package com.kurovale.station.train;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long>
{
    List<Train> findByActiveIsTrue();

    Optional<Train> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Train> findByIdEqualsAndActiveIsFalse(Long id);

    // Development
    Train findByIdEquals(Long id);
}
