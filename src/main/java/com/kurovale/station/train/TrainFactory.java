package com.kurovale.station.train;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TrainFactory
{
    @Bean
    CommandLineRunner seedDatabaseWithTrains(TrainRepository trainRepository)
    {
        List<Train> trains = Arrays.asList(new Train("Ram Van B350", 1992, 100),
                new Train("CX", 1989, 200),
                new Train("Maxima", 2007, 300),
                new Train("Jetta", 2008, 400),
                new Train("Integra", 1995, 500));
        return args -> trainRepository.saveAll(trains);
    }
}
