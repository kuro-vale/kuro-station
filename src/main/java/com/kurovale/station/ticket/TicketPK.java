package com.kurovale.station.ticket;

import com.kurovale.station.passenger.Passenger;
import com.kurovale.station.travel.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class TicketPK implements Serializable
{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id", updatable = false, nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_id", updatable = false, nullable = false)
    private Travel travel;

    public TicketPK(Passenger passenger, Travel travel)
    {
        this.passenger = passenger;
        this.travel = travel;
    }
}
