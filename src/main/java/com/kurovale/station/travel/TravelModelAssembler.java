package com.kurovale.station.travel;

import com.kurovale.station.station.StationModelAssembler;
import com.kurovale.station.train.TrainModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class TravelModelAssembler implements RepresentationModelAssembler<Travel, EntityModel<TravelDTO>>
{
    private final StationModelAssembler stationModelAssembler;
    private final TrainModelAssembler trainModelAssembler;

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

        EntityModel<TravelDTO> entityModel = EntityModel.of(travelDTO,
            linkTo(methodOn(TravelController.class).show(travel.getId())).withSelfRel(),
            linkTo(methodOn(TravelShowAllController.class).showAll()).withRel("travels"));

        if (travel.getStatus() == TravelStatus.PREPARING)
        {
            entityModel.add(linkTo(methodOn(TravelController.class).start(travel.getId())).withRel("start"),
                    linkTo(methodOn(TravelController.class).cancel(travel.getId())).withRel("cancel"));
        }
        if (travel.getStatus() == TravelStatus.TRAVELING)
        {
            entityModel.add(linkTo(methodOn(TravelController.class).finish(travel.getId())).withRel("finish"));
        }

        return entityModel;
    }

    @Override
    public CollectionModel<EntityModel<TravelDTO>> toCollectionModel(Iterable<? extends Travel> travels)
    {
        return RepresentationModelAssembler.super.toCollectionModel(travels);
    }
}
