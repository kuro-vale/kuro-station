package com.kurovale.station.travel;

import com.kurovale.station.station.Station;
import com.kurovale.station.station.StationRepository;
import com.kurovale.station.train.Train;
import com.kurovale.station.train.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class TravelShowAllController
{
    private final TravelRepository travelRepository;
    private final StationRepository stationRepository;
    private final TrainRepository trainRepository;
    private final TravelModelAssembler assembler;

    @GetMapping("/travels")
    ResponseEntity<?> showAll()
    {
        return showAllPaginated(1);
    }

    @GetMapping(value = "/travels", params = {"page"})
    ResponseEntity<?> showAllPaginated(@RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Travel> travels = travelRepository.findByStatusIs(pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllPaginated(travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"departure_station"})
    ResponseEntity<?> showAllByDepartureStation(@RequestParam(value = "departure_station") String departureStation)
    {
        return showAllByDepartureStationPaginated(departureStation, 1);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "page"})
    ResponseEntity<?> showAllByDepartureStationPaginated(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Station station = stationRepository.findFirstByNameLike("%" + departureStation + "%");

        Page<Travel> travels = travelRepository.findByDepartureStationEqualsAndStatusIs(station, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationPaginated(departureStation, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationPaginated(departureStation, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationPaginated(departureStation, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationPaginated(departureStation, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationPaginated(departureStation, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"arrival_station"})
    ResponseEntity<?> showAllByArrivalStation(@RequestParam(value = "arrival_station") String arrivalStation)
    {
        return showAllByArrivalStationPaginated(arrivalStation, 1);
    }

    @GetMapping(value = "/travels", params = {"arrival_station", "page"})
    ResponseEntity<?> showAllByArrivalStationPaginated(@RequestParam(value = "arrival_station") String arrivalStation, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Station station = stationRepository.findFirstByNameLike("%" + arrivalStation + "%");

        Page<Travel> travels = travelRepository.findByArrivalStationEqualsAndStatusIs(station, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationPaginated(arrivalStation, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationPaginated(arrivalStation, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationPaginated(arrivalStation, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationPaginated(arrivalStation, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationPaginated(arrivalStation, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "arrival_station"})
    ResponseEntity<?> showAllByDepartureStationAndArrivalStation(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "arrival_station") String arrivalStation)
    {
        return showAllByDepartureStationAndArrivalStationPaginated(departureStation, arrivalStation, 1);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "arrival_station", "page"})
    ResponseEntity<?> showAllByDepartureStationAndArrivalStationPaginated(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "arrival_station") String arrivalStation, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Station station1 = stationRepository.findFirstByNameLike("%" + departureStation + "%");
        Station station2 = stationRepository.findFirstByNameLike("%" + arrivalStation + "%");

        Page<Travel> travels = travelRepository.findByDepartureStationEqualsAndArrivalStationEqualsAndStatusIs(station1, station2, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndArrivalStationPaginated(departureStation, arrivalStation, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndArrivalStationPaginated(departureStation, arrivalStation, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndArrivalStationPaginated(departureStation, arrivalStation, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndArrivalStationPaginated(departureStation, arrivalStation, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndArrivalStationPaginated(departureStation, arrivalStation, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"train"})
    ResponseEntity<?> showAllByTrain(@RequestParam(value = "train") String train)
    {
        return showAllByTrainPaginated(train, 1);
    }

    @GetMapping(value = "/travels", params = {"train", "page"})
    ResponseEntity<?> showAllByTrainPaginated(@RequestParam(value = "train") String train, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Train train1 = trainRepository.findByIdEquals(Long.parseLong(train));

        Page<Travel> travels = travelRepository.findByTrainEqualsAndStatusIs(train1, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByTrainPaginated(train, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByTrainPaginated(train, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByTrainPaginated(train, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByTrainPaginated(train, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByTrainPaginated(train, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "train"})
    ResponseEntity<?> showAllByDepartureStationAndTrain(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "train") String train)
    {
        return showAllByDepartureStationAndTrainPaginated(departureStation, train, 1);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "train", "page"})
    ResponseEntity<?> showAllByDepartureStationAndTrainPaginated(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "train") String train, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Station station = stationRepository.findFirstByNameLike("%" + departureStation + "%");

        Train train1 = trainRepository.findByIdEquals(Long.parseLong(train));

        Page<Travel> travels = travelRepository.findByDepartureStationEqualsAndTrainEqualsAndStatusIs(station, train1, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndTrainPaginated(departureStation, train, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndTrainPaginated(departureStation, train, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndTrainPaginated(departureStation, train, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndTrainPaginated(departureStation, train, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationAndTrainPaginated(departureStation, train, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"arrival_station", "train"})
    ResponseEntity<?> showAllByArrivalStationAndTrain(@RequestParam(value = "arrival_station") String arrivalStation, @RequestParam(value = "train") String train)
    {
        return showAllByArrivalStationAndTrainPaginated(arrivalStation, train, 1);
    }

    @GetMapping(value = "/travels", params = {"arrival_station", "train", "page"})
    ResponseEntity<?> showAllByArrivalStationAndTrainPaginated(@RequestParam(value = "arrival_station") String arrivalStation, @RequestParam(value = "train") String train, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Station station = stationRepository.findFirstByNameLike("%" + arrivalStation + "%");

        Train train1 = trainRepository.findByIdEquals(Long.parseLong(train));

        Page<Travel> travels = travelRepository.findByArrivalStationEqualsAndTrainEqualsAndStatusIs(station, train1, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationAndTrainPaginated(arrivalStation, train, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationAndTrainPaginated(arrivalStation, train, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationAndTrainPaginated(arrivalStation, train, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationAndTrainPaginated(arrivalStation, train, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByArrivalStationAndTrainPaginated(arrivalStation, train, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "arrival_station", "train"})
    ResponseEntity<?> showAllByDepartureStationArrivalStationAndTrain(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "arrival_station") String arrivalStation, @RequestParam(value = "train") String train)
    {
        return showAllByDepartureStationArrivalStationAndTrainPaginated(departureStation, arrivalStation, train, 1);
    }

    @GetMapping(value = "/travels", params = {"departure_station", "arrival_station", "train", "page"})
    ResponseEntity<?> showAllByDepartureStationArrivalStationAndTrainPaginated(@RequestParam(value = "departure_station") String departureStation, @RequestParam(value = "arrival_station") String arrivalStation, @RequestParam(value = "train") String train, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Station station1 = stationRepository.findFirstByNameLike("%" + departureStation + "%");

        Station station2 = stationRepository.findFirstByNameLike("%" + arrivalStation + "%");

        Train train1 = trainRepository.findByIdEquals(Long.parseLong(train));

        Page<Travel> travels = travelRepository.findByDepartureStationEqualsAndArrivalStationEqualsAndTrainEqualsAndStatusIs(station1, station2, train1, pageable, TravelStatus.PREPARING);

        if (travels.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<TravelDTO>> collectionModel = assembler.toCollectionModel(travels);

        if (travels.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationArrivalStationAndTrainPaginated(departureStation, arrivalStation, train, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (travels.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationArrivalStationAndTrainPaginated(departureStation, arrivalStation, train, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationArrivalStationAndTrainPaginated(departureStation, arrivalStation, train, page)).withSelfRel(),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationArrivalStationAndTrainPaginated(departureStation, arrivalStation, train, 1)).withRel("first"),
                linkTo(methodOn(TravelShowAllController.class).showAllByDepartureStationArrivalStationAndTrainPaginated(departureStation, arrivalStation, train, travels.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }
}
