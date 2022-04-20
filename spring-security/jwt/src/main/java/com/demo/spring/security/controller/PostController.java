package com.demo.spring.security.controller;

import com.demo.spring.security.domain.Post;
import com.demo.spring.security.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository repository;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> post(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> put(@PathVariable Long id, @RequestBody Post post) {
        throw new IllegalArgumentException("IllegalArgumentException");
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> get() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> get(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findById(id));
    }
}
