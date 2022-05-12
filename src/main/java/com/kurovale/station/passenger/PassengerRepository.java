package com.kurovale.station.passenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long>
{
    Optional<Passenger> findByIdEqualsAndActiveIsTrue(Long id);
    Optional<Passenger> findByIdEqualsAndActiveIsFalse(Long id);
    List<Passenger> findByActiveIsTrue();
}
