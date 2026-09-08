package com.example.autoposterbackend.dto.request;

import lombok.Getter;

@Getter
public class EditAccountRequest {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
