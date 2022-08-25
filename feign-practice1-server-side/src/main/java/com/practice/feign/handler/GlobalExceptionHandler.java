package com.practice.feign.handler;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError<String>> exc(Exception exc) {
        return ResponseEntity
            .status(504)
            .body(
                new RestError<String>(
                    exc.getMessage(),
                    "this is exception comes from feign-server",
                    UUID.randomUUID().toString(),
                    LocalDateTime.now()
                )
            );
    }

    record RestError<T>(T data, String message, String requestId, LocalDateTime processedAt) {}

}

