package com.kurovale.station.train;

import com.kurovale.station.auth.Role;
import com.kurovale.station.exceptions.EntityNotFoundException;
import com.kurovale.station.exceptions.EntityStatus;
import com.kurovale.station.exceptions.EntityStatusException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;

@RestController
public class TrainController
{
    private final TrainRepository repository;
    private final TrainModelAssembler assembler;

    public TrainController(TrainRepository repository, TrainModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/trains")
    @RolesAllowed(Role.ADMIN)
    ResponseEntity<?> store(@RequestBody Train train)
    {
        try
        {
            EntityModel<?> entityModel = assembler.toModel(repository.save(train));
            return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
        } catch (ConstraintViolationException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Invalid entry")
                    .withDetail("Train model year must be between 1980 and 2020 and Train capacity must be between 100 and 500"));
        }
    }

    @GetMapping("/trains/{id}")
    ResponseEntity<?> show(@PathVariable Long id)
    {
        Train train = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Train.class));

        if (!train.getActive())
        {
            return ResponseEntity.noContent().build();
        }
        EntityModel<TrainDTO> entityModel = assembler.toModel(train);

        return ResponseEntity.ok().body(entityModel);
    }

    @DeleteMapping("/trains/{id}")
    @RolesAllowed(Role.ADMIN)
    ResponseEntity<?> disable(@PathVariable Long id)
    {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Train.class));

        repository.findByIdEqualsAndActiveIsTrue(id)
                .map(train ->
                {
                    train.setActive(false);
                    return repository.save(train);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.DISABLED, Train.class));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/trains/{id}/enable")
    @RolesAllowed(Role.ADMIN)
    ResponseEntity<?> enable(@PathVariable Long id)
    {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Train.class));

        Train enabledTrain = repository.findByIdEqualsAndActiveIsFalse(id)
                .map(train ->
                {
                    train.setActive(true);
                    return repository.save(train);
                }).orElseThrow(() -> new EntityStatusException(EntityStatus.ENABLED, Train.class));

        EntityModel<TrainDTO> entityModel = assembler.toModel(enabledTrain);

        return ResponseEntity.ok().body(entityModel);
    }
}
