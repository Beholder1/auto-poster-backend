package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.ScriptRequest;
import com.example.autoposterbackend.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @PostMapping("/{userId}")
    private void runScript(@PathVariable Integer userId, @RequestBody ScriptRequest scriptRequest) throws InterruptedException {
        scriptService.runScript(userId, scriptRequest);
    }
}
