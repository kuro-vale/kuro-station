package com.kurovale.station.passenger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long>
{
    Passenger findByIdEquals(Long id);
}
