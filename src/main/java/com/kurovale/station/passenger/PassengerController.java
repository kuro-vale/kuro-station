package com.kurovale.station.passenger;

import com.kurovale.station.exceptions.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

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
        List<Passenger> passengers = repository.findByActiveIsTrue();

        if (passengers.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        CollectionModel<EntityModel<PassengerDTO>> collectionModel = assembler.toCollectionModel(passengers);

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
    ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.findByIdEqualsAndActiveIsTrue(id)
                .map(passenger ->
                {
                    passenger.setActive(false);
                    return repository.save(passenger);
                }).orElseThrow(() -> new EntityNotFoundException(id, Passenger.class));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/passengers/{id}/activate")
    ResponseEntity<?> activateUser(@PathVariable Long id)
    {
        Passenger activatedPassenger = repository.findById(id)
                .map(passenger ->
                {
                    passenger.setActive(true);
                    return repository.save(passenger);
                }).orElseThrow(() -> new EntityNotFoundException(id, Passenger.class));

        EntityModel<PassengerDTO> entityModel = assembler.toModel(activatedPassenger);

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
                    .withTitle("Invalid email format")
                    .withDetail("Email format must be: foo@foo.foo"));
        }
    }
}
