package com.kurovale.station.travel;

import com.kurovale.station.station.StationModelAssembler;
import com.kurovale.station.train.TrainModelAssembler;
import com.kurovale.station.train.TrainShowAllController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TravelModelAssembler implements RepresentationModelAssembler<Travel, EntityModel<TravelDTO>>
{
    private final StationModelAssembler stationModelAssembler;
    private final TrainModelAssembler trainModelAssembler;

    public TravelModelAssembler(StationModelAssembler stationModelAssembler, TrainModelAssembler trainModelAssembler)
    {
        this.stationModelAssembler = stationModelAssembler;
        this.trainModelAssembler = trainModelAssembler;
    }

    @Override
    public EntityModel<TravelDTO> toModel(Travel travel)
    {
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setId(travel.getId());
        travelDTO.setDepartureStation(stationModelAssembler.toModel(travel.getDepartureStation()));
        travelDTO.setArrivalStation(stationModelAssembler.toModel(travel.getArrivalStation()));
        travelDTO.setDepartureDate(travel.getDepartureDate());
        travelDTO.setArrivalDate(travel.getArrivalDate());
        travelDTO.setStatus(travel.getStatus());
        travelDTO.setPrice(travel.getPrice());
        travelDTO.setTrain(trainModelAssembler.toModel(travel.getTrain()));

        return EntityModel.of(travelDTO,
                linkTo(methodOn(TravelController.class).show(travel.getId())).withSelfRel(),
                linkTo(methodOn(TrainShowAllController.class).showAll()).withRel("travels"));
    }

    @Override
    public CollectionModel<EntityModel<TravelDTO>> toCollectionModel(Iterable<? extends Travel> travels)
    {
        return RepresentationModelAssembler.super.toCollectionModel(travels);
    }
}
