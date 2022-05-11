package com.kurovale.station.train;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long>
{
    Train findByIdEquals(Long id);
}
