package com.kurovale.station.travel;

import com.kurovale.station.station.Station;
import com.kurovale.station.train.Train;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "travels")
public class Travel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_station_id", updatable = false, nullable = false)
    private Station departureStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_station_id", updatable = false, nullable = false)
    private Station arrivalStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", updatable = false, nullable = false)
    private Train train;

    @Column(name = "departure_date", updatable = false, nullable = false)
    private LocalDateTime departureDate;
    @Column(name = "arrival_date", updatable = false)
    private LocalDateTime arrivalDate;
    @Column(name = "status", nullable = false)
    private TravelStatus status = TravelStatus.DEPARTING;
    @Column(name = "price", updatable = false, nullable = false)
    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Travel(Station departureStation, Station arrivalStation, Train train, LocalDateTime departureDate, Integer price)
    {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.train = train;
        this.departureDate = departureDate;
        this.price = price;
    }
}
