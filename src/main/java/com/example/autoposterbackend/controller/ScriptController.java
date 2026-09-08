package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.RefreshScriptRequest;
import com.example.autoposterbackend.dto.request.ScriptRequest;
import com.example.autoposterbackend.dto.response.ScriptAccountsResponse;
import com.example.autoposterbackend.dto.response.ScriptLocationsResponse;
import com.example.autoposterbackend.dto.response.ScriptProductsResponse;
import com.example.autoposterbackend.entity.User;
import com.example.autoposterbackend.service.ScriptDataService;
import com.example.autoposterbackend.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;
    private final ScriptDataService scriptDataService;

    @GetMapping("/accounts")
    public ScriptAccountsResponse getScriptAccounts(@AuthenticationPrincipal User user,
                                                    @RequestParam(required = false) List<Integer> ids) {
        return scriptDataService.getAccounts(user.getId(), ids);
    }

    @GetMapping("/products")
    public ScriptProductsResponse getScriptProducts(@AuthenticationPrincipal User user,
                                                    @RequestParam(required = false) List<Integer> ids) {
        return scriptDataService.getProducts(user.getId(), ids);
    }

    @GetMapping("/locations")
    public ScriptLocationsResponse getScriptLocations(@AuthenticationPrincipal User user) {
        return scriptDataService.getLocations(user.getId());
    }

    @PostMapping
    public void runScript(@AuthenticationPrincipal User user, @RequestBody ScriptRequest scriptRequest) throws InterruptedException {
        scriptService.runScript(user.getId(), scriptRequest);
    }

    @PostMapping("/refresh")
    public void runRefreshScript(@AuthenticationPrincipal User user, @RequestBody RefreshScriptRequest refreshScriptRequest) {
        scriptService.runRefreshScript(user.getId(), refreshScriptRequest);
    }
}
