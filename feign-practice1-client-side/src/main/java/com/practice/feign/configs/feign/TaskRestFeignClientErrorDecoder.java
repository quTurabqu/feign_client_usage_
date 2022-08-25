package com.practice.feign.configs.feign;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.feign.clients.TaskRestClient;
import com.practice.feign.exceptions.CustomFeignClientException;
import com.practice.feign.handler.RestClientError;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Component - // it can be given for right feign-client
// if ErrorDecoder is not given as bean, so it must be specified in application.properties
public class TaskRestFeignClientErrorDecoder implements ErrorDecoder {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static final ErrorDecoder DEFAULT_ERROR_DECODER = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() == 504) {
            log.error(">> FeignExc handling: methodKey: {}", methodKey);
            // return new RuntimeException(">> FeignExc handling - methodKey: " + methodKey);
            try {
                // final byte[] responseBodyAsByte = Util.toByteArray();
                // final String responseBody = new String(responseBodyAsByte);
                final RestClientError<String> error = OBJECT_MAPPER.readValue(response.body().asInputStream(), RestClientError.class);
                // return new CustomFeignClientException(error);
                return new RetryableException(response.status(), response.reason(), response.request().httpMethod(), null, response.request());

            } catch (IOException exc) {
                log.error(">> unexpected IOException: {}", exc.getMessage());
            }
           
        }
        return DEFAULT_ERROR_DECODER.decode(methodKey, response);
    }
    
}
