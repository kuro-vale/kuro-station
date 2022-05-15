package com.kurovale.station.station;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long>
{
    Optional<Station> findByIdEqualsAndActiveIsTrue(Long id);

    Optional<Station> findByIdEqualsAndActiveIsFalse(Long id);

    Station findFirstByNameLike(@Param("name") String name);

    Page<Station> findByActiveIsTrue(Pageable pageable);

    Page<Station> findByNameLikeAndActiveIsTrue(String name, Pageable pageable);

    Page<Station> findByAddressLikeAndActiveIsTrue(String address, Pageable pageable);

    Page<Station> findByNameLikeAndAddressLikeAndActiveIsTrue(String name, String address, Pageable pageable);

    Page<Station> findByPhoneLikeAndActiveIsTrue(String phone, Pageable pageable);

    Page<Station> findByNameLikeAndPhoneLikeAndActiveIsTrue(String name, String phone, Pageable pageable);

    Page<Station> findByAddressLikeAndPhoneLikeAndActiveIsTrue(String address, String phone, Pageable pageable);

    Page<Station> findByNameLikeAndAddressLikeAndPhoneLikeAndActiveIsTrue(String name, String address, String phone, Pageable pageable);
}
