package com.kurovale.station.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket
{
    @EmbeddedId
    private TicketPK id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Ticket(TicketPK id)
    {
        this.id = id;
    }
}
