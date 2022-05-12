package com.kurovale.station.station;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class StationDTO extends RepresentationModel<StationDTO>
{
    private Long id;
    private String name;
    private String address;
    private String phone;
}
