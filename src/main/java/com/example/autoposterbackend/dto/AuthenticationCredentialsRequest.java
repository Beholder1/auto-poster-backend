package com.example.autoposterbackend.dto;

import lombok.Data;

@Data
public class AuthenticationCredentialsRequest {
    private String email;
    private String password;
    private Boolean rememberMe;
}
