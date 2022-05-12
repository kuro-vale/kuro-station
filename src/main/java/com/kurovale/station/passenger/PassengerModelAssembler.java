package com.kurovale.station.passenger;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PassengerModelAssembler implements RepresentationModelAssembler<Passenger, EntityModel<PassengerDTO>>
{
    @Override
    public EntityModel<PassengerDTO> toModel(Passenger passenger)
    {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setId(passenger.getId());
        passengerDTO.setName(passenger.getName());
        passengerDTO.setEmail(passenger.getEmail());

        return EntityModel.of(passengerDTO,
                linkTo(methodOn(PassengerController.class).show(passenger.getId())).withSelfRel(),
                linkTo(methodOn(PassengerController.class).showAll()).withRel("passengers"));
    }

    @Override
    public CollectionModel<EntityModel<PassengerDTO>> toCollectionModel(Iterable<? extends Passenger> passengers)
    {
        return RepresentationModelAssembler.super.toCollectionModel(passengers).add(linkTo(methodOn(PassengerController.class).showAll()).withSelfRel());
    }
}
