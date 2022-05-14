package com.kurovale.station.train;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class TrainDTO extends RepresentationModel<TrainDTO>
{
    private Long id;
    private String model;
    @JsonProperty("model_year")
    private Integer modelYear;
    private Integer capacity;

}
