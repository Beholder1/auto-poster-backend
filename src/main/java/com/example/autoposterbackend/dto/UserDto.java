package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
