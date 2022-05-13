package com.kurovale.station.train;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class TrainDTO extends RepresentationModel<TrainDTO>
{
    private Long id;
    private String model;
    private Integer modelYear;
    private Integer capacity;

}
