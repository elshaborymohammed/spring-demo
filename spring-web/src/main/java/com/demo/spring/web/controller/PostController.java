package com.demo.spring.web.controller;

import com.demo.spring.web.domain.Post;
import com.demo.spring.web.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @PostMapping
    ResponseEntity<?> post(@RequestBody Post post) {
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
        Iterable<Post> all = repository.findAll();
        all.forEach(log::info);
        return ResponseEntity.ok(all);
//        return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.findById(id));
    }
}
