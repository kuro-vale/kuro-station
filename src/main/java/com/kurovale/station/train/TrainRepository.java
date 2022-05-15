package com.kurovale.station.train;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long>
{
    Train findByIdEquals(Long id);

    Optional<Train> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Train> findByIdEqualsAndActiveIsFalse(Long id);

    Page<Train> findByActiveIsTrue(Pageable pageable);

    Page<Train> findByModelLikeAndActiveIsTrue(String name, Pageable pageable);
}
