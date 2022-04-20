package com.demo.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class, args);
    }

    @PostConstruct
    void CommandLineRunner() {
        System.out.println("WebApplication.CommandLineRunner");
    }
}
