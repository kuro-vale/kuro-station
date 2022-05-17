package com.kurovale.station.station;

import com.kurovale.station.auth.Role;
import com.kurovale.station.exceptions.EntityNotFoundException;
import com.kurovale.station.exceptions.EntityStatus;
import com.kurovale.station.exceptions.EntityStatusException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;

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
    @PostMapping("/stations")
    @RolesAllowed(Role.ADMIN)
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
    @RolesAllowed(Role.ADMIN)
    ResponseEntity<?> disable(@PathVariable Long id)
    {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Station.class));

        repository.findByIdEqualsAndActiveIsTrue(id)
                .map(station ->
                {
                    station.setActive(false);
                    return repository.save(station);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.DISABLED, Station.class));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/stations/{id}/enable")
    @RolesAllowed(Role.ADMIN)
    ResponseEntity<?> enable(@PathVariable Long id)
    {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Station.class));

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
