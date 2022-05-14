package com.kurovale.station.passenger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
public class PassengerShowAllController
{
    private final PassengerRepository repository;
    private final PassengerModelAssembler assembler;

    public PassengerShowAllController(PassengerRepository repository, PassengerModelAssembler assembler)
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
                    linkTo(methodOn(PassengerShowAllController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (passengers.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerShowAllController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(PassengerShowAllController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(PassengerShowAllController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(PassengerShowAllController.class).showAllPaginated(passengers.getTotalPages())).withRel("last"));

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
                    linkTo(methodOn(PassengerShowAllController.class).showAllByNamePaginated(name, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (passengers.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerShowAllController.class).showAllByNamePaginated(name, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(PassengerShowAllController.class).showAllByNamePaginated(name, page)).withSelfRel(),
                linkTo(methodOn(PassengerShowAllController.class).showAllByNamePaginated(name, 1)).withRel("first"),
                linkTo(methodOn(PassengerShowAllController.class).showAllByNamePaginated(name, passengers.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/passengers", params = {"email"})
    ResponseEntity<?> showAllByEmail(@RequestParam(value = "email") String email)
    {
        return showAllByEmailPaginated(email, 1);
    }

    @GetMapping(value = "/passengers", params = {"email", "page"})
    ResponseEntity<?> showAllByEmailPaginated(@RequestParam(value = "email") String email, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Passenger> passengers = repository.findByEmailLikeAndActiveIsTrue("%" + email + "%", pageable);

        if (passengers.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<PassengerDTO>> collectionModel = assembler.toCollectionModel(passengers);

        if (passengers.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerShowAllController.class).showAllByEmailPaginated(email, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (passengers.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerShowAllController.class).showAllByEmailPaginated(email, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(PassengerShowAllController.class).showAllByEmailPaginated(email, page)).withSelfRel(),
                linkTo(methodOn(PassengerShowAllController.class).showAllByEmailPaginated(email, 1)).withRel("first"),
                linkTo(methodOn(PassengerShowAllController.class).showAllByEmailPaginated(email, passengers.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/passengers", params = {"name", "email"})
    ResponseEntity<?> showAllByNameAndEmail(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email)
    {
        return showAllByNameAndEmailPaginated(name, email, 1);
    }

    @GetMapping(value = "/passengers", params = {"name", "email", "page"})
    ResponseEntity<?> showAllByNameAndEmailPaginated(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Passenger> passengers = repository.findByNameLikeAndEmailLikeAndActiveIsTrue("%" + name + "%", "%" + email + "%", pageable);

        if (passengers.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<PassengerDTO>> collectionModel = assembler.toCollectionModel(passengers);

        if (passengers.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerShowAllController.class).showAllByNameAndEmailPaginated(name, email, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (passengers.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(PassengerShowAllController.class).showAllByNameAndEmailPaginated(name, email, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(PassengerShowAllController.class).showAllByNameAndEmailPaginated(name, email, page)).withSelfRel(),
                linkTo(methodOn(PassengerShowAllController.class).showAllByNameAndEmailPaginated(name, email, 1)).withRel("first"),
                linkTo(methodOn(PassengerShowAllController.class).showAllByNameAndEmailPaginated(name, email, passengers.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }
}
