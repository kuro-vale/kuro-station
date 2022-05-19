package com.kurovale.station.ticket;

import com.kurovale.station.travel.TravelStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, TicketPK>
{
    Page<Ticket> findById_Passenger_IdEqualsAndId_Travel_StatusEquals(Long id, TravelStatus status, Pageable pageable);

    Optional<Ticket> findById_Passenger_IdEqualsAndId_Travel_IdEquals(Long id, Long id1);

    long countById_Travel_Id(Long id);
}
