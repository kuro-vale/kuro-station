package com.kurovale.station.auth;

import com.kurovale.station.passenger.Passenger;
import com.kurovale.station.passenger.PassengerModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final PassengerModelAssembler assembler;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request)
    {
        try
        {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.email, request.password));

            Passenger passenger = (Passenger) authentication.getPrincipal();

            Instant now = Instant.now();

            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                    .issuer("kuro-store-api")
                    .issuedAt(now)
                    .subject(passenger.getId() + ", " + passenger.getUsername())
                    .claim("roles", scope).build();

            String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token)
                    .body(assembler.toModel(passenger));
        } catch (BadCredentialsException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
