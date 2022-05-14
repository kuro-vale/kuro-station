package com.kurovale.station.travel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kurovale.station.station.Station;
import com.kurovale.station.train.Train;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @JsonProperty("departure_station")
    @JoinColumn(name = "departure_station_id", updatable = false, nullable = false)
    private Station departureStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("arrival_station")
    @JoinColumn(name = "arrival_station_id", updatable = false, nullable = false)
    private Station arrivalStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", updatable = false, nullable = false)
    private Train train;

    @Column(name = "departure_date", updatable = false, nullable = false)
    @JsonProperty("departure_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @Future
    private LocalDateTime departureDate;
    @Column(name = "arrival_date", updatable = false)
    private LocalDateTime arrivalDate;
    @Column(name = "status", nullable = false)
    private TravelStatus status = TravelStatus.PREPARING;
    @Column(name = "price", updatable = false, nullable = false)
    @Min(10)
    @Max(1000)
    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Travel(LocalDateTime departureDate, Integer price)
    {
        this.departureDate = departureDate;
        this.price = price;
    }
}
