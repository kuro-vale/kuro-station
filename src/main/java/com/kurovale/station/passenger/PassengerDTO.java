package com.kurovale.station.passenger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class PassengerDTO extends RepresentationModel<PassengerDTO>
{
    private Long id;
    private String name;
    private String email;
}
