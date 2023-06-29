package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateAccountRequest;
import com.example.autoposterbackend.dto.response.AccountsDetailsResponse;
import com.example.autoposterbackend.dto.response.AccountsResponse;
import com.example.autoposterbackend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{userId}")
    public AccountsResponse getUserAccounts(@PathVariable Integer userId) {
        return accountService.getUserAccounts(userId);
    }

    @GetMapping("/{userId}/details")
    public AccountsDetailsResponse getUserAccountsDetails(@PathVariable Integer userId, @RequestParam(required = false) String name) {
        return accountService.getUserAccountsDetails(userId, name);
    }

    @DeleteMapping("/{userId}/{accountId}")
    public void deleteAccount(@PathVariable Integer userId, @PathVariable Integer accountId) {
        accountService.deleteAccount(userId, accountId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@PathVariable Integer userId, @RequestBody CreateAccountRequest request) throws Exception {
        accountService.createAccount(userId, request);
    }
}
