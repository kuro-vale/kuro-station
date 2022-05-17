package com.kurovale.station.auth;

import com.kurovale.station.passenger.Passenger;
import com.kurovale.station.passenger.PassengerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminFactory
{
    @Bean
    CommandLineRunner seedDatabaseWithAdmin(PassengerRepository passengerRepository, PasswordEncoder passwordEncoder)
    {
        return args ->
        {
            Passenger admin = new Passenger();
            admin.setName("Julian");
            admin.setEmail("kuro@vale.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            passengerRepository.save(admin);
        };
    }
}
