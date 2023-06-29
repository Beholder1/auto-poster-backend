package com.example.autoposterbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {
    @Value("${password.seed}")
    private String passwordSeed;

    @Value("${spring.mail.username}")
    private String appEmail;

    @Value("${jwt.secret}")
    private String jwtSecret;
}
