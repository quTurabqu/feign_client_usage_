package com.practice.feign.handler;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practice.feign.exceptions.CustomFeignClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomFeignClientException.class)
    public ResponseEntity<RestClientError<String>> exc(CustomFeignClientException exc) {
        return ResponseEntity.status(504).body(exc.getClientError());
    }


}

