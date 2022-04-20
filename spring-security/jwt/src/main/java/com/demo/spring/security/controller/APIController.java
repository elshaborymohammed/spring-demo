package com.demo.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        throw new EntityNotFoundException("EntityNotFoundException");
    }
}
