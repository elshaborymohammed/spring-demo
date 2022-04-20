package com.demo.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JWTApplication {
    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }

    @PostConstruct
    void CommandLineRunner() {
        System.out.println("WebApplication.CommandLineRunner");
    }
}
