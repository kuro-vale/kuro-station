package com.kurovale.station.travel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long>
{
    Travel findByIdEquals(Long id);
}
