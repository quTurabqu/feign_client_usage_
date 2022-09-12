package com.practice.feign.configs.feign;

import java.time.LocalDateTime;
import java.util.UUID;

import com.practice.feign.exceptions.CustomFeignClientException;
import com.practice.feign.handler.RestClientError;

import feign.RetryableException;
import feign.Retryer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor // is required
// @Component // it can be given for right feign-client
// if TaskRestFeignClientRetryer is not given as bean, so it must be specified in application.properties
public class TaskRestFeignClientRetryer implements feign.Retryer {

    private int retryMaxAttempt;
    private long retryInterval;
    private int attempt = 1;

    public TaskRestFeignClientRetryer(int retryMaxAttempt, long retryInterval) {
        this.retryMaxAttempt = retryMaxAttempt;
        this.retryInterval = retryInterval;
    }

    @Override
    public void continueOrPropagate(RetryableException e) { 
        log.error(">> {}: retry attempt: {}, msg: {}", this.getClass().getSimpleName(), attempt, e.getMessage());

        if(attempt++ == retryMaxAttempt){
            throw new CustomFeignClientException(e.getMessage(), e);
        }
        try {
            Thread.sleep(retryInterval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        
    }

    @Override
    public Retryer clone() {
        return new TaskRestFeignClientRetryer(3, 1500);
    }
    
}
