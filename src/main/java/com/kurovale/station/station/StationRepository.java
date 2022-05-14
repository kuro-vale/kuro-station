package com.kurovale.station.station;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long>
{
    Optional<Station> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Station> findByIdEqualsAndActiveIsFalse(Long id);

    Page<Station> findByActiveIsTrue(Pageable pageable);

    Page<Station> findByNameLikeAndActiveIsTrue(String name, Pageable pageable);

    Page<Station> findByAddressLikeAndActiveIsTrue(String address, Pageable pageable);

    Page<Station> findByNameLikeAndAddressLikeAndActiveIsTrue(String name, String address, Pageable pageable);

    Page<Station> findByPhoneLikeAndActiveIsTrue(String phone, Pageable pageable);

    Page<Station> findByNameLikeAndPhoneLikeAndActiveIsTrue(String name, String phone, Pageable pageable);

    Page<Station> findByAddressLikeAndPhoneLikeAndActiveIsTrue(String address, String phone, Pageable pageable);

    Page<Station> findByNameLikeAndAddressLikeAndPhoneLikeAndActiveIsTrue(String name, String address, String phone, Pageable pageable);

    //    Development
    Station findByIdEquals(Long id);

}
