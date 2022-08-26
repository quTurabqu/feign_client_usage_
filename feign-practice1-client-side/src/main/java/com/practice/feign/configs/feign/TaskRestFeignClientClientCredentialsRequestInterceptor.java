package com.practice.feign.configs.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskRestFeignClientClientCredentialsRequestInterceptor implements RequestInterceptor {

    private static final String CLIENT_ID = "123456";
    private static final String CLIENT_SECRET = "secret";

    @Override
    public void apply(RequestTemplate template) {
        log.info(">> {} apply({})", this.getClass().getSimpleName(), template.request());

        // adding client_id and client_secret as header
        template.header("Client-Id", CLIENT_ID);
        template.header("Client-Secret", CLIENT_SECRET);

    }
    
}
