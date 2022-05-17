package com.kurovale.station.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority
{
    public static final String ADMIN = "ADMIN";
    public static final String PASSENGER = "PASSENGER";

    private String authority;
}
