package com.kurovale.station.train;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TrainModelAssembler implements RepresentationModelAssembler<Train, EntityModel<TrainDTO>>
{
    @Override
    public EntityModel<TrainDTO> toModel(Train train)
    {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(train.getId());
        trainDTO.setModel(train.getModel());
        trainDTO.setModelYear(train.getModelYear());
        trainDTO.setCapacity(train.getCapacity());

        return EntityModel.of(trainDTO,
                linkTo(methodOn(TrainController.class).show(train.getId())).withSelfRel(),
                linkTo(methodOn(TrainController.class).showAll()).withRel("trains"));
    }

    @Override
    public CollectionModel<EntityModel<TrainDTO>> toCollectionModel(Iterable<? extends Train> trains)
    {
        return RepresentationModelAssembler.super.toCollectionModel(trains).add(linkTo(methodOn(TrainController.class).showAll()).withSelfRel());
    }
}
