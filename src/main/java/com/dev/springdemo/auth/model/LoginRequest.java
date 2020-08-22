package com.dev.springdemo.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
