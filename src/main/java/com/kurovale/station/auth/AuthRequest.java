package com.kurovale.station.auth;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class AuthRequest
{
    @NotNull
    @Email
    String email;

    @NotNull
    String password;
}
