package com.kurovale.station.passenger;

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
public class PassengerController
{
    private final PassengerRepository repository;
    private final PassengerModelAssembler assembler;

    public PassengerController(PassengerRepository repository, PassengerModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/passengers")
    ResponseEntity<?> showAll()
    {
        return showAllPaginated(1);
    }

    @GetMapping(value = "/passengers", params = {"page"})
    ResponseEntity<?> showAllPaginated(@RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Passenger> passengers = repository.findByActiveIsTrue(pageable);

        if (passengers.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<PassengerDTO>> collectionModel = assembler.toCollectionModel(passengers);

        if (passengers.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (passengers.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(PassengerController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(PassengerController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(PassengerController.class).showAllPaginated(passengers.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/passengers", params = {"name"})
    ResponseEntity<?> showAllByName(@RequestParam(value = "name") String name)
    {
        return showAllByNamePaginated(name, 1);
    }

    @GetMapping(value = "/passengers", params = {"name", "page"})
    ResponseEntity<?> showAllByNamePaginated(@RequestParam(value = "name") String name, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Passenger> passengers = repository.findByNameLikeAndActiveIsTrue("%" + name + "%", pageable);

        if (passengers.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<PassengerDTO>> collectionModel = assembler.toCollectionModel(passengers);

        if (passengers.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerController.class).showAllByNamePaginated(name, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (passengers.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerController.class).showAllByNamePaginated(name, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(PassengerController.class).showAllByNamePaginated(name, page)).withSelfRel(),
                linkTo(methodOn(PassengerController.class).showAllByNamePaginated(name, 1)).withRel("first"),
                linkTo(methodOn(PassengerController.class).showAllByNamePaginated(name, passengers.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @PostMapping("/passengers")
    ResponseEntity<?> store(@RequestBody Passenger passenger)
    {
        return checkConstrains(passenger, HttpStatus.CREATED);
    }

    @GetMapping("/passengers/{id}")
    ResponseEntity<?> show(@PathVariable Long id)
    {
        Passenger passenger = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Passenger.class));

        if (!passenger.getActive())
        {
            return ResponseEntity.noContent().build();
        }

        EntityModel<PassengerDTO> entityModel = assembler.toModel(passenger);

        return ResponseEntity.ok().body(entityModel);
    }

    @PutMapping("/passengers/{id}")
    ResponseEntity<?> update(@RequestBody Passenger newPassenger, @PathVariable Long id)
    {
        Passenger updatedPassenger = repository.findByIdEqualsAndActiveIsTrue(id)
                .map(passenger ->
                {
                    passenger.setName(newPassenger.getName());
                    passenger.setEmail(newPassenger.getEmail());
                    return passenger;
                }).orElseThrow(() -> new EntityNotFoundException(id, Passenger.class));

        return checkConstrains(updatedPassenger, HttpStatus.OK);
    }

    @DeleteMapping("/passengers/{id}")
    ResponseEntity<?> disable(@PathVariable Long id)
    {
        repository.findByIdEqualsAndActiveIsTrue(id)
                .map(passenger ->
                {
                    passenger.setActive(false);
                    return repository.save(passenger);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.DISABLED, Passenger.class));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/passengers/{id}/enable")
    ResponseEntity<?> enable(@PathVariable Long id)
    {
        Passenger enabledPassenger = repository.findByIdEqualsAndActiveIsFalse(id)
                .map(passenger ->
                {
                    passenger.setActive(true);
                    return repository.save(passenger);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.ENABLED, Passenger.class));

        EntityModel<PassengerDTO> entityModel = assembler.toModel(enabledPassenger);

        return ResponseEntity.ok().body(entityModel);
    }

    private ResponseEntity<?> checkConstrains(Passenger passenger, HttpStatus status)
    {

        try
        {
            EntityModel<PassengerDTO> entityModel = assembler.toModel(repository.save(passenger));
            return ResponseEntity.status(status).body(entityModel);
        } catch (DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Forbidden")
                    .withDetail("Email has been already taken"));
        } catch (ConstraintViolationException | TransactionSystemException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Invalid format")
                    .withDetail("Email format must be: foo@foo.foo"));
        }
    }
}
