package com.kurovale.station.station;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StationFactory
{
    @Bean
    CommandLineRunner seedDatabaseWithStations(StationRepository stationRepository)
    {
        List<Station> stations = Arrays.asList(new Station("Dayton", "59726 Moland Parkway", "(367) 3696667"),
                new Station("Autumn Leaf", "1 Kings Plaza", "(804) 6355075"),
                new Station("Monica", "03 Crest Line Circle", "(551) 6049352"),
                new Station("Manitowish", "738 Heath Court", "(593) 5023556"),
                new Station("Shelley", "86893 Northland Court", "(221) 6202593"),
                new Station("Merrick", "09 Arizona Crossing", "(993) 1736928"),
                new Station("Forest Run", "70 Stang Road", "(146) 1227852"),
                new Station("Schurz", "1 Carey Road", "(648) 6202328"),
                new Station("Sloan", "85 Lakewood Pass", "(236) 5531449"),
                new Station("Armistice", "68351 Elka Terrace", "(470) 6861528"));
        return args -> stationRepository.saveAll(stations);
    }
}
