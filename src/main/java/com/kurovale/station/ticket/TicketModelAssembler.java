package com.kurovale.station.ticket;

import com.kurovale.station.passenger.PassengerModelAssembler;
import com.kurovale.station.travel.TravelModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class TicketModelAssembler implements RepresentationModelAssembler<Ticket, EntityModel<TicketDTO>>
{
    private final TravelModelAssembler travelModelAssembler;
    private final PassengerModelAssembler passengerModelAssembler;

    @Override
    public EntityModel<TicketDTO> toModel(Ticket ticket)
    {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setPassenger(passengerModelAssembler.toModel(ticket.getId().getPassenger()));
        ticketDTO.setTravel(travelModelAssembler.toModel(ticket.getId().getTravel()));
        ticketDTO.setBoughtAt(ticket.getCreatedAt());

        return EntityModel.of(ticketDTO).add(
                linkTo(methodOn(TicketController.class).show(ticket.getId().getTravel().getId())).withSelfRel(),
                linkTo(methodOn(TicketController.class).showAll()).withRel("tickets"));
    }

    @Override
    public CollectionModel<EntityModel<TicketDTO>> toCollectionModel(Iterable<? extends Ticket> tickets)
    {
        return RepresentationModelAssembler.super.toCollectionModel(tickets);
    }
}
