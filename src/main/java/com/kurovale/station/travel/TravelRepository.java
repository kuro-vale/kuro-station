package com.kurovale.station.travel;

import com.kurovale.station.station.Station;
import com.kurovale.station.train.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long>
{
    Page<Travel> findByStatusIs(Pageable pageable, TravelStatus status);

    Optional<Travel> findByIdEqualsAndStatusIsNotIn(Long id, Collection<TravelStatus> statuses);

    Page<Travel> findByDepartureStationEqualsAndStatusIs(Station departureStation, Pageable pageable, TravelStatus status);

    Page<Travel> findByArrivalStationEqualsAndStatusIs(Station arrivalStation, Pageable pageable, TravelStatus status);

    Page<Travel> findByDepartureStationEqualsAndArrivalStationEqualsAndStatusIs(Station departureStation, Station arrivalStation, Pageable pageable, TravelStatus status);

    Page<Travel> findByTrainEqualsAndStatusIs(Train train, Pageable pageable, TravelStatus status);

    Page<Travel> findByDepartureStationEqualsAndTrainEqualsAndStatusIs(Station departureStation, Train train, Pageable pageable, TravelStatus status);

    Page<Travel> findByArrivalStationEqualsAndTrainEqualsAndStatusIs(Station arrivalStation, Train train, Pageable pageable, TravelStatus status);

    Page<Travel> findByDepartureStationEqualsAndArrivalStationEqualsAndTrainEqualsAndStatusIs(Station departureStation, Station arrivalStation, Train train, Pageable pageable, TravelStatus status);
}
