package com.practice.controller;

import com.practice.client.CbarCurrencyRateReaderClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/currencies")
public record CurrenyRateController(CbarCurrencyRateReaderClient currencyRateReaderClient) {

    @GetMapping("/rate")
    public ResponseEntity<Object> currencies() {
        return ResponseEntity.ok(
                currencyRateReaderClient.readCurrencyRate(
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ".xml"
                )
        );
    }
}
