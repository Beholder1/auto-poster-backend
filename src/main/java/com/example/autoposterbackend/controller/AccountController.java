package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.response.AccountsResponse;
import com.example.autoposterbackend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{userId}")
    public AccountsResponse getUserAccounts(@PathVariable Integer userId) {
        return accountService.getUserAccounts(userId);
    }
}
