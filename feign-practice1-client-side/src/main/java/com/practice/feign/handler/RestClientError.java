package com.practice.feign.handler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestClientError<T> {
    
    private T data;
    private String message;
    private String requestId;
    private LocalDateTime processedAt;

}
