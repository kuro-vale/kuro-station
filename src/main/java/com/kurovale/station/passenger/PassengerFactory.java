package com.kurovale.station.passenger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class PassengerFactory
{
    @Bean
    CommandLineRunner seedDatabase(PassengerRepository passengerRepository)
    {
        return args ->
        {
            List<Passenger> passengers = Arrays.asList(new Passenger("Marnia Bavister", "50261 Kingsford Alley", "(416) 5417681"),
                    new Passenger("Blanca Oades", "6 Hanson Crossing", "(432) 1903749"),
                    new Passenger("Justine Cole", "31222 Hoffman Drive", "(919) 3233218"),
                    new Passenger("Talia Elfleet", "702 Lakeland Street", "(377) 5064143"),
                    new Passenger("Kara Jullian", "7 Jenifer Pass", "(145) 6936826"),
                    new Passenger("Guglielma Chazier", "5881 Banding Way", "(940) 4302265"),
                    new Passenger("Denny Lorinez", "76 Texas Crossing", "(469) 8921854"),
                    new Passenger("Dorey Wagnerin", "923 Anzinger Pass", "(251) 8601796"),
                    new Passenger("Raddy McCallam", "9137 Superior Junction", "(848) 8925768"),
                    new Passenger("Elfrida Cello", "3004 Texas Plaza", "(897) 5049769"));
            passengerRepository.saveAll(passengers);
        };
    }
}
