package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AccountsResponse {
    private List<AccountDto> accounts;
}
