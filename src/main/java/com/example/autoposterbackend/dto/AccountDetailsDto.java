package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetailsDto {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public AccountDetailsDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.password = account.getPassword();
    }
}
