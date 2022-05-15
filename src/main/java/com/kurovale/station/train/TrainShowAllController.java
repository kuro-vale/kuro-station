package com.kurovale.station.train;

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
public class TrainShowAllController
{
    private final TrainRepository repository;
    private final TrainModelAssembler assembler;

    public TrainShowAllController(TrainRepository repository, TrainModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/trains")
    ResponseEntity<?> showAll()
    {
        return showAllPaginated(1);
    }

    @GetMapping(value = "/trains", params = {"page"})
    ResponseEntity<?> showAllPaginated(@RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Train> trains = repository.findByActiveIsTrue(pageable);

        if (trains.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TrainDTO>> collectionModel = assembler.toCollectionModel(trains);

        if (trains.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TrainShowAllController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (trains.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TrainShowAllController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TrainShowAllController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(TrainShowAllController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(TrainShowAllController.class).showAllPaginated(trains.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/trains", params = {"model"})
    ResponseEntity<?> showAllByModel(@RequestParam(value = "model") String model)
    {
        return showAllByModelPaginated(model, 1);
    }

    @GetMapping(value = "/trains", params = {"model", "page"})
    ResponseEntity<?> showAllByModelPaginated(@RequestParam(value = "model") String model, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Train> trains = repository.findByModelLikeAndActiveIsTrue("%" + model + "%", pageable);

        if (trains.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TrainDTO>> collectionModel = assembler.toCollectionModel(trains);

        if (trains.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TrainShowAllController.class).showAllByModelPaginated(model, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (trains.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TrainShowAllController.class).showAllByModelPaginated(model, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TrainShowAllController.class).showAllByModelPaginated(model, page)).withSelfRel(),
                linkTo(methodOn(TrainShowAllController.class).showAllByModelPaginated(model, 1)).withRel("first"),
                linkTo(methodOn(TrainShowAllController.class).showAllByModelPaginated(model, trains.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }
}
