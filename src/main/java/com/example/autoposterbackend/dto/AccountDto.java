package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Account;
import lombok.Getter;

@Getter
public class AccountDto {
    private Integer id;
    private String name;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
    }
}
