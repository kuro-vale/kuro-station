package com.kurovale.station.passenger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long>
{
    Optional<Passenger> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Passenger> findByIdEqualsAndActiveIsFalse(Long id);

    Page<Passenger> findByActiveIsTrue(Pageable pageable);

    Page<Passenger> findByNameLikeAndActiveIsTrue(String name, Pageable pageable);

    Page<Passenger> findByEmailLikeAndActiveIsTrue(String email, Pageable pageable);

    Page<Passenger> findByNameLikeAndEmailLikeAndActiveIsTrue(String name, String email, Pageable pageable);

    Optional<Passenger> findByEmail(String email);
}
