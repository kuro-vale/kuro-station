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
    CommandLineRunner seedDatabaseWithPassengers(PassengerRepository passengerRepository)
    {
        return args ->
        {
            List<Passenger> passengers = Arrays.asList(new Passenger("Marnia Bavister", "bjerred0@go.com"),
                    new Passenger("Blanca Oades", "rswanborrow1@flavors.me"),
                    new Passenger("Justine Cole", "aseedull2@zimbio.com"),
                    new Passenger("Talia Elfleet", "brevens3@engadget.com"),
                    new Passenger("Kara Jullian", "cayliff4@paginegialle.it"),
                    new Passenger("Guglielma Chazier", "ecliburn5@npr.org"),
                    new Passenger("Denny Lorinez", "sbartholomew6@time.com"),
                    new Passenger("Dorey Wagnerin", "fswoffer7@auda.org.au"),
                    new Passenger("Raddy McCallam", "vblunt8@hostgator.com"),
                    new Passenger("Elfrida Cello", "ahemerijk9@amazon.de"));
            passengerRepository.saveAll(passengers);
        };
    }
}
