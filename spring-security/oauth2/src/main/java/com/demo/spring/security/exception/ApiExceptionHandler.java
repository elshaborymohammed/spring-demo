package com.demo.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<?> handle(IllegalStateException e) {
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    ResponseEntity<?> handle(EntityNotFoundException e) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({RuntimeException.class})
    ResponseEntity<?> handle(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
    }

    @ExceptionHandler({AccessDeniedException.class})
    ResponseEntity<?> handle(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
    }
}
