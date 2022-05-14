package com.kurovale.station.station;

import com.kurovale.station.exceptions.EntityNotFoundException;
import com.kurovale.station.exceptions.EntityStatus;
import com.kurovale.station.exceptions.EntityStatusException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StationController
{
    private final StationRepository repository;
    private final StationModelAssembler assembler;

    public StationController(StationRepository repository, StationModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/stations")
    ResponseEntity<?> showAll()
    {
        return showAllPaginated(1);
    }

    @GetMapping(value = "/stations", params = {"page"})
    ResponseEntity<?> showAllPaginated(@RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByActiveIsTrue(pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous"));
        }
        collectionModel.add(linkTo(methodOn(StationController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(StationController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(StationController.class).showAllPaginated(stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"name"})
    ResponseEntity<?> showAllByName(@RequestParam(value = "name") String name)
    {
        return showAllByNamePaginated(name, 1);
    }

    @GetMapping(value = "/stations", params = {"name", "page"})
    ResponseEntity<?> showAllByNamePaginated(@RequestParam(value = "name") String name, @RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByNameLikeAndActiveIsTrue("%" + name + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationController.class).showAllByNamePaginated(name, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationController.class).showAllByNamePaginated(name, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous"));
        }
        collectionModel.add(linkTo(methodOn(StationController.class).showAllByNamePaginated(name, page)).withSelfRel(),
                linkTo(methodOn(StationController.class).showAllByNamePaginated(name, 1)).withRel("first"),
                linkTo(methodOn(StationController.class).showAllByNamePaginated(name, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @PostMapping("/stations")
    ResponseEntity<?> store(@RequestBody Station station)
    {
        return checkConstrains(station, HttpStatus.CREATED);
    }

    @GetMapping("/stations/{id}")
    ResponseEntity<?> show(@PathVariable Long id)
    {
        Station station = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Station.class));

        if (!station.getActive())
        {
            return ResponseEntity.noContent().build();
        }

        EntityModel<StationDTO> entityModel = assembler.toModel(station);

        return ResponseEntity.ok().body(entityModel);
    }

    @PutMapping("/stations/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Station newStation)
    {
        Station updatedStation = repository.findByIdEqualsAndActiveIsTrue(id)
                .map(station ->
                {
                    station.setName(newStation.getName());
                    station.setAddress(newStation.getAddress());
                    station.setPhone(newStation.getPhone());
                    return station;
                }).orElseThrow(() -> new EntityNotFoundException(id, Station.class));

        return checkConstrains(updatedStation, HttpStatus.OK);
    }

    @DeleteMapping("/stations/{id}")
    ResponseEntity<?> disable(@PathVariable Long id)
    {
        repository.findByIdEqualsAndActiveIsTrue(id)
                .map(station ->
                {
                    station.setActive(false);
                    return repository.save(station);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.DISABLED, Station.class));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/stations/{id}/enable")
    ResponseEntity<?> enable(@PathVariable Long id)
    {
        Station enabledStation = repository.findByIdEqualsAndActiveIsFalse(id)
                .map(station -> {
                    station.setActive(true);
                    return repository.save(station);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.ENABLED, Station.class));

        EntityModel<StationDTO> entityModel = assembler.toModel(enabledStation);

        return ResponseEntity.ok().body(entityModel);
    }

    private ResponseEntity<?> checkConstrains(Station station, HttpStatus status)
    {
        try
        {
            EntityModel<StationDTO> entityModel = assembler.toModel(repository.save(station));
            return ResponseEntity.status(status).body(entityModel);
        } catch (DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Forbidden")
                    .withDetail("Address or Phone are already used"));
        } catch (ConstraintViolationException | TransactionSystemException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Invalid format")
                    .withDetail("Phone format must be (###) #######"));
        }
    }
}
