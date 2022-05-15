package com.kurovale.station.travel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kurovale.station.station.StationDTO;
import com.kurovale.station.train.TrainDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class TravelDTO extends RepresentationModel<TravelDTO>
{
    private Long id;
    @JsonProperty("departure_station")
    private EntityModel<StationDTO> departureStation;
    @JsonProperty("arrival_station")
    private EntityModel<StationDTO> arrivalStation;
    @JsonProperty("departure_date")
    private LocalDateTime departureDate;
    @JsonProperty("arrival_date")
    private LocalDateTime arrivalDate;
    private TravelStatus status;
    private Integer price;
    private EntityModel<TrainDTO> train;
}
