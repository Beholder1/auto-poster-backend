package com.example.autoposterbackend.dto.request;

import lombok.Getter;

@Getter
public class CreateAccountRequest {
    private String name;
    private String email;
    private String password;
}
