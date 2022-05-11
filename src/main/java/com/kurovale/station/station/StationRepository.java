package com.kurovale.station.station;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long>
{
    Station findByIdEquals(Long id);
}
