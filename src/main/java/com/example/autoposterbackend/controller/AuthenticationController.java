package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.*;
import com.example.autoposterbackend.entity.User;
import com.example.autoposterbackend.repository.UserRepository;
import com.example.autoposterbackend.service.UserService;
import com.example.autoposterbackend.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationCredentialsRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            if (user.getBanned()) {
                throw new BadCredentialsException("User is banned");
            }
            user.setLastLogin(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user, user.getId(), user.getRealUsername(), request.getRememberMe())).body(new UserDto(user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user) {
        try {
            Boolean isTokenValid = jwtUtil.validateToken(token, user);
            return ResponseEntity.ok(isTokenValid);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> sendForgotPasswordEmail(@RequestBody ForgotPasswordRequest email) {
        return ResponseEntity.ok(userService.sendForgotPasswordEmail(email));
    }

    @PostMapping(path = "/password/confirm")
    public ResponseEntity<?> confirmPassword(@RequestBody PasswordResetDto passwordResetDto) {
        return ResponseEntity.ok(userService.confirmPassword(passwordResetDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @GetMapping(path = "/register/confirm")
    public String confirm(@RequestParam String token) {
        return userService.confirmToken(token);
    }
}
