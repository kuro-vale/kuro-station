package com.kurovale.station.travel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long>
{
    List<Travel> findByStatusIs(TravelStatus status);

    Optional<Travel> findByIdEqualsAndStatusIsNotIn(Long id, Collection<TravelStatus> statuses);
}
