package com.practice.feign.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

@RestControllerAdvice
public record GlobalExceptionHandler(ObjectMapper objectMapper) {
    
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorData> feignClient(FeignException exc) {
        Map<String, Object> errorData = null;

        if(exc.responseBody().isPresent()) {
            byte[] data = exc.responseBody().get().array();
            try {
                errorData = objectMapper.readValue(data, Map.class);
            } catch (IOException ioException) {
                return ResponseEntity.status(504).body(new ErrorData<String>(ioException.getClass().getSimpleName(), "Can't parse FeignClientException errorData", LocalDateTime.now()));
            }
        }

        return ResponseEntity.status(504).body(new ErrorData<Map<String, Object>>(errorData, exc.getMessage(), LocalDateTime.now()));
    }


    private static record ErrorData<T>(T data, String message, LocalDateTime processedAt) {}
    
}

