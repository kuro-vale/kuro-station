package com.kurovale.station.travel;

import com.kurovale.station.exceptions.EntityNotFoundException;
import com.kurovale.station.station.Station;
import com.kurovale.station.station.StationRepository;
import com.kurovale.station.train.Train;
import com.kurovale.station.train.TrainRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class TravelController
{
    private final TravelRepository travelRepository;
    private final StationRepository stationRepository;
    private final TrainRepository trainRepository;
    private final TravelModelAssembler assembler;

    public TravelController(TravelRepository travelRepository, TravelModelAssembler assembler, StationRepository stationRepository, TrainRepository trainRepository)
    {
        this.travelRepository = travelRepository;
        this.stationRepository = stationRepository;
        this.trainRepository = trainRepository;
        this.assembler = assembler;
    }

    @PostMapping("/travels")
    ResponseEntity<?> store(@RequestBody Travel travel)
    {
        Station departureStation = stationRepository.findByIdEqualsAndActiveIsTrue(travel.getDepartureStation().getId())
                .orElseThrow(() -> new EntityNotFoundException(travel.getDepartureStation().getId(), Station.class));

        Station arrivalStation = stationRepository.findByIdEqualsAndActiveIsTrue(travel.getArrivalStation().getId())
                .orElseThrow(() -> new EntityNotFoundException(travel.getArrivalStation().getId(), Station.class));

        Train train = trainRepository.findByIdEqualsAndActiveIsTrue(travel.getTrain().getId())
                .orElseThrow(() -> new EntityNotFoundException(travel.getTrain().getId(), Train.class));

        travel.setDepartureStation(departureStation);
        travel.setArrivalStation(arrivalStation);
        travel.setTrain(train);
        try
        {
            EntityModel<?> entityModel = assembler.toModel(travelRepository.save(travel));
            return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
        } catch (ConstraintViolationException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Problem.create()
                    .withTitle("Invalid Entry")
                    .withDetail("Departure date must be in a near future, and price must be between 10 and 1000"));
        }
    }

    @GetMapping("/travels/{id}")
    ResponseEntity<?> show(@PathVariable Long id)
    {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Travel.class));

        EntityModel<TravelDTO> entityModel = assembler.toModel(travel);

        return ResponseEntity.ok().body(entityModel);
    }

    @DeleteMapping("/travels/{id}")
    ResponseEntity<?> cancel(@PathVariable Long id)
    {
        Collection<TravelStatus> statuses = Arrays.asList(TravelStatus.TRAVELING, TravelStatus.ARRIVED, TravelStatus.CANCELLED);
        Travel canceledTravel = travelRepository.findByIdEqualsAndStatusIsNotIn(id, statuses)
                .map(travel ->
                {
                    travel.setStatus(TravelStatus.CANCELLED);
                    return travelRepository.save(travel);
                }).orElseThrow(() -> new TravelStatusException(statuses));

        EntityModel<TravelDTO> entityModel = assembler.toModel(canceledTravel);

        return ResponseEntity.ok().body(entityModel);
    }

    @PatchMapping("/travels/{id}/start")
    ResponseEntity<?> start(@PathVariable Long id)
    {
        Collection<TravelStatus> statuses = Arrays.asList(TravelStatus.ARRIVED, TravelStatus.CANCELLED, TravelStatus.TRAVELING);
        Travel startedTravel = travelRepository.findByIdEqualsAndStatusIsNotIn(id, statuses)
                .map(travel ->
                {
                    travel.setStatus(TravelStatus.TRAVELING);
                    travel.setDepartureDate(LocalDateTime.now().plusSeconds(10));
                    return travelRepository.save(travel);
                }).orElseThrow(() -> new TravelStatusException(statuses));

        EntityModel<TravelDTO> entityModel = assembler.toModel(startedTravel);

        return ResponseEntity.ok().body(entityModel);
    }

    @PatchMapping("/travels/{id}/end")
    ResponseEntity<?> end(@PathVariable Long id)
    {
        Collection<TravelStatus> statuses = Arrays.asList(TravelStatus.PREPARING, TravelStatus.CANCELLED, TravelStatus.ARRIVED);
        Travel endedTravel = travelRepository.findByIdEqualsAndStatusIsNotIn(id, statuses)
                .map(travel ->
                {
                    travel.setStatus(TravelStatus.ARRIVED);
                    travel.setArrivalDate(LocalDateTime.now().plusSeconds(10));
                    return travel;
                }).orElseThrow(() -> new TravelStatusException(statuses));

        try
        {
            travelRepository.save(endedTravel);
        } catch (TransactionSystemException ignored) {}

        EntityModel<TravelDTO> entityModel = assembler.toModel(endedTravel);

        return ResponseEntity.ok().body(entityModel);
    }
}
