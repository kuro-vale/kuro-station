package com.kurovale.station.ticket;

import com.kurovale.station.auth.Role;
import com.kurovale.station.exceptions.EntityNotFoundException;
import com.kurovale.station.passenger.Passenger;
import com.kurovale.station.passenger.PassengerRepository;
import com.kurovale.station.travel.Travel;
import com.kurovale.station.travel.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

import static com.kurovale.station.passenger.PassengerController.getLoggedPassengerId;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class TicketController
{
    private final TicketRepository ticketRepository;
    private final TravelRepository travelRepository;
    private final PassengerRepository passengerRepository;
    private final TicketModelAssembler assembler;

    @GetMapping("/tickets")
    @RolesAllowed(Role.PASSENGER)
    ResponseEntity<?> showAll()
    {
        return showAllPaginated(1);
    }

    @GetMapping(value = "/tickets", params = {"page"})
    @RolesAllowed(Role.PASSENGER)
    ResponseEntity<?> showAllPaginated(@RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Long loggedPassengerId = getLoggedPassengerId();

        // Get logged passenger tickets
        Page<Ticket> tickets = ticketRepository.findById_Passenger_IdEquals(loggedPassengerId, pageable);

        if (tickets.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TicketDTO>> collectionModel = assembler.toCollectionModel(tickets);

        if (tickets.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TicketController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (tickets.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TicketController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TicketController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(TicketController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(TicketController.class).showAllPaginated(tickets.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @PostMapping("/travels/{id}/tickets")
    @RolesAllowed(Role.PASSENGER)
    public ResponseEntity<?> buy(@PathVariable Long id)
    {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Travel.class));

        Long loggedPassengerId = getLoggedPassengerId();
        Passenger loggedPassenger = passengerRepository.findByIdEqualsAndActiveIsTrue(loggedPassengerId)
                .orElseThrow(() -> new EntityNotFoundException(id, Passenger.class));

        Optional<Ticket> boughtTicket = ticketRepository.findById_Passenger_IdEqualsAndId_Travel_IdEquals(loggedPassengerId, travel.getId());

        if (boughtTicket.isPresent())
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Ticket already bought")
                    .withDetail("You already bought the ticket to the travel with id: " + travel.getId()));
        }

        TicketPK ticketPK = new TicketPK(loggedPassenger, travel);

        EntityModel<TicketDTO> entityModel = assembler.toModel(ticketRepository.save(new Ticket(ticketPK)));

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @GetMapping("/travels/{id}/tickets")
    @RolesAllowed(Role.PASSENGER)
    ResponseEntity<?> show(@PathVariable Long id)
    {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Travel.class));

        Long loggedPassengerId = getLoggedPassengerId();

        Ticket boughtTicket = ticketRepository.findById_Passenger_IdEqualsAndId_Travel_IdEquals(loggedPassengerId, travel.getId())
                .orElseThrow(() -> new EntityNotFoundException(id, Ticket.class));

        EntityModel<TicketDTO> entityModel = assembler.toModel(boughtTicket);

        return ResponseEntity.ok().body(entityModel);
    }
}
