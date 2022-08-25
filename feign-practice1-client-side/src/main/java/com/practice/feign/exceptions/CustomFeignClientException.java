package com.practice.feign.exceptions;

import com.practice.feign.handler.RestClientError;

public class CustomFeignClientException extends RuntimeException {

    private final RestClientError clientError;

    public CustomFeignClientException(RestClientError clientError) {
        this.clientError = clientError;
    }

    public RestClientError getClientError() {
        return clientError;
    }

}
