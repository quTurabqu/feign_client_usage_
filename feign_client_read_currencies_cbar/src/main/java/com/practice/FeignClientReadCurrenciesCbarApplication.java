package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.practice.client")
@SpringBootApplication
public class FeignClientReadCurrenciesCbarApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientReadCurrenciesCbarApplication.class, args);
    }

}
