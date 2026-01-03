package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.RefreshScriptRequest;
import com.example.autoposterbackend.dto.request.ScriptRequest;
import com.example.autoposterbackend.entity.User;
import com.example.autoposterbackend.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @PostMapping
    public void runScript(@AuthenticationPrincipal User user, @RequestBody ScriptRequest scriptRequest) throws InterruptedException {
        scriptService.runScript(user.getId(), scriptRequest);
    }

    @PostMapping("/refresh")
    public void runRefreshScript(@AuthenticationPrincipal User user, @RequestBody RefreshScriptRequest refreshScriptRequest) {
        scriptService.runRefreshScript(user.getId(), refreshScriptRequest);
    }
}
