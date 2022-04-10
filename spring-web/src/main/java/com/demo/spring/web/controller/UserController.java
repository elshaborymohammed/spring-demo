package com.demo.spring.web.controller;

import com.demo.spring.web.domain.Post;
import com.demo.spring.web.domain.User;
import com.demo.spring.web.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    ResponseEntity<?> post(@RequestBody User post) {
        log.info(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @RequestBody Post post) {
        throw new IllegalArgumentException("IllegalArgumentException");
//        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    @GetMapping
    ResponseEntity<?> get() {
        Iterable<User> all = repository.findAll();
        all.forEach(log::info);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findById(id));
    }
}
