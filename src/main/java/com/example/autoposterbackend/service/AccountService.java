package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.AccountDto;
import com.example.autoposterbackend.dto.response.AccountsResponse;
import com.example.autoposterbackend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountsResponse getUserAccounts(Integer userId) {
        return new AccountsResponse(accountRepository.findByUserId(userId).stream().map(AccountDto::new).toList());
    }
}
