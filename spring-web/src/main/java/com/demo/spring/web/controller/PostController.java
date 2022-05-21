package com.demo.spring.web.controller;

import com.demo.spring.web.domain.Post;
import com.demo.spring.web.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostRepository repository;

    @Autowired
    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> post(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> put(@PathVariable Long id, @RequestBody Post post) {
        throw new IllegalArgumentException("IllegalArgumentException");
    }

    @GetMapping
//    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> get() {
        return ResponseEntity.ok(
                IntStream.range(0, 1)
                        .mapToObj(operand -> Post.builder().id(operand).content("content").manufacturer("manufacturer").build())
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> get(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findById(id));
    }
}
