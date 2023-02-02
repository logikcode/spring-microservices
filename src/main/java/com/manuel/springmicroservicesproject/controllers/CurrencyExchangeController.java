package com.manuel.springmicroservicesproject.controllers;

import com.manuel.springmicroservicesproject.bean.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final Environment env;
    @GetMapping("/currency-exchange/from/{base}/to/{dest}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String base,
                                                  @PathVariable String dest){
        String port = env.getProperty("local.server.port");
        return new CurrencyExchange(1000L, base, dest, BigDecimal.valueOf(20), port);
    }
}
