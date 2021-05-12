package com.demo.spring.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class APIController {

    @PostMapping
    ResponseEntity<?> post() {
        throw new IllegalStateException("IllegalStateException");
    }

    @PutMapping
    ResponseEntity<?> put() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }

    @PatchMapping
    ResponseEntity<?> patch() {
        throw new NoSuchElementException("NoSuchElementException");
    }

    @GetMapping
    ResponseEntity<?> get() {
//        javax.persistence.Exception
//        throw new EntityNotFoundException("EntityNotFoundException");
        throw new RuntimeException("EntityNotFoundException");
    }
}
