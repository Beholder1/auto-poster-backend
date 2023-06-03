package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.AccountDetailsDto;
import com.example.autoposterbackend.dto.AccountDto;
import com.example.autoposterbackend.dto.response.AccountsDetailsResponse;
import com.example.autoposterbackend.dto.response.AccountsResponse;
import com.example.autoposterbackend.entity.Account;
import com.example.autoposterbackend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountsResponse getUserAccounts(Integer userId) {
        return new AccountsResponse(accountRepository.findByUserId(userId).stream().map(AccountDto::new).toList());
    }

    public AccountsDetailsResponse getUserAccountsDetails(Integer userId, String name) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        List<Account> accounts = accountRepository.findByUserIdAndNameLike(userId, name, pageable);
        Integer pages = (int) Math.ceil((double) accountRepository.countAllByUserIdAndNameLike(userId, name) / 20);
        return new AccountsDetailsResponse(accounts.stream().map(AccountDetailsDto::new).toList(), pages);
    }
}
