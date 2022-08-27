package com.practice.feign.exceptions;

import feign.FeignException;

public class CustomFeignClientException extends RuntimeException {

    public CustomFeignClientException(String message, FeignException cause) {
        super(message, cause);
    }

}
