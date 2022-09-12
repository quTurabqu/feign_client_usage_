package com.practice.client;

import com.practice.domain.ValuteCurs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        url = "https://www.cbar.az/currencies/",
        value = "cbarCurrencyRateReaderClient"
)
public interface CbarCurrencyRateReaderClient {

    @GetMapping(value = "/{date}", consumes = MediaType.TEXT_XML_VALUE)
    ValuteCurs readCurrencyRate(@PathVariable String date);

}
