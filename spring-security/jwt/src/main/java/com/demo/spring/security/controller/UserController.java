package com.demo.spring.security.controller;

import com.demo.spring.security.domain.Post;
import com.demo.spring.security.domain.User;
import com.demo.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @PostMapping
    ResponseEntity<?> post(@RequestBody User post) {
        log.info(post.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @RequestBody Post post) {
        throw new IllegalArgumentException("IllegalArgumentException");
    }

    @GetMapping
    ResponseEntity<?> get() {
        List<User> all = repository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findById(id));
    }
}
