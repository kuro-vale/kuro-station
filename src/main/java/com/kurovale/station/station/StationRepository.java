package com.kurovale.station.station;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long>
{
    List<Station> findByActiveIsTrue();

    Optional<Station> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Station> findByIdEqualsAndActiveIsFalse(Long id);

    //    Development
    Station findByIdEquals(Long id);

}
