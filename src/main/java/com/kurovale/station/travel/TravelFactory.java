package com.kurovale.station.travel;

import com.kurovale.station.station.StationRepository;
import com.kurovale.station.train.TrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class TravelFactory
{
    @Bean
    CommandLineRunner seedDatabaseWithTravels(TravelRepository travelRepository, StationRepository stationRepository, TrainRepository trainRepository)
    {
        return args ->
                travelRepository.save(new Travel(stationRepository.findByIdEquals(1L), stationRepository.findByIdEquals(2L),
                        trainRepository.findByIdEquals(1L),
                        LocalDateTime.of(2022, 8, 21, 5, 0), 25));
    }
}
