package com.example.autoposterbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AutoPosterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoPosterBackendApplication.class, args);
    }

}
