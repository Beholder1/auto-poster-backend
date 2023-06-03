package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.AccountDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AccountsDetailsResponse {
    private List<AccountDetailsDto> accounts;
    private Integer pages;
}
