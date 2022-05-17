package com.kurovale.station.passenger;

import com.kurovale.station.auth.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class PassengerFactory
{
    private final PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner seedDatabaseWithPassengers(PassengerRepository passengerRepository)
    {
        return args ->
        {
            Passenger admin = new Passenger();
            admin.setName("Passenger");
            admin.setEmail("test@test.com");
            admin.setPassword(passwordEncoder.encode("user123"));
            admin.setRole(Role.PASSENGER);
            passengerRepository.save(admin);
        };
    }
}
