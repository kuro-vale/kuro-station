package com.kurovale.station.station;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long>
{
    Optional<Station> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Station> findByIdEqualsAndActiveIsFalse(Long id);

    Page<Station> findByActiveIsTrue(Pageable pageable);

    Page<Station> findByNameLikeAndActiveIsTrue(String name, Pageable pageable);

    //    Development
    Station findByIdEquals(Long id);

}
