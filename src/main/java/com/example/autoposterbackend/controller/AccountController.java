package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateAccountRequest;
import com.example.autoposterbackend.dto.response.AccountsDetailsResponse;
import com.example.autoposterbackend.dto.response.AccountsResponse;
import com.example.autoposterbackend.entity.User;
import com.example.autoposterbackend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public AccountsResponse getUserAccounts(@AuthenticationPrincipal User user) {
        return accountService.getUserAccounts(user.getId());
    }

    @GetMapping("/details")
    public AccountsDetailsResponse getUserAccountsDetails(@AuthenticationPrincipal User user, @RequestParam(required = false) String name) {
        return accountService.getUserAccountsDetails(user.getId(), name);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        accountService.deleteAccount(user.getId(), accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@AuthenticationPrincipal User user, @RequestBody CreateAccountRequest request) throws Exception {
        accountService.createAccount(user.getId(), request);
    }
}
