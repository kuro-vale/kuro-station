package com.kurovale.station.station;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StationModelAssembler implements RepresentationModelAssembler<Station, EntityModel<StationDTO>>
{
    @Override
    public EntityModel<StationDTO> toModel(Station station)
    {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setName(station.getName());
        stationDTO.setAddress(station.getAddress());
        stationDTO.setPhone(station.getPhone());

        return EntityModel.of(stationDTO,
                linkTo(methodOn(StationController.class).show(station.getId())).withSelfRel(),
                linkTo(methodOn(StationController.class).showAll()).withRel("stations"));
    }

    @Override
    public CollectionModel<EntityModel<StationDTO>> toCollectionModel(Iterable<? extends Station> stations)
    {
        return RepresentationModelAssembler.super.toCollectionModel(stations);
    }
}
