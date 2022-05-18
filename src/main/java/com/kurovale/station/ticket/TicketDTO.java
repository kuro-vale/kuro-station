package com.kurovale.station.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kurovale.station.passenger.PassengerDTO;
import com.kurovale.station.travel.TravelDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDTO
{
    private EntityModel<PassengerDTO> passenger;
    private EntityModel<TravelDTO> travel;
    @JsonProperty("bought_at")
    private LocalDateTime boughtAt;
}
