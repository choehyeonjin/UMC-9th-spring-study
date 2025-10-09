package com.example.umc9th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.example.umc9th.domain")
@EnableJpaRepositories(basePackages = "com.example.umc9th.domain")
@EnableJpaAuditing
@SpringBootApplication
public class Umc9thApplication {
    public static void main(String[] args) {
        SpringApplication.run(Umc9thApplication.class, args);
    }
}
