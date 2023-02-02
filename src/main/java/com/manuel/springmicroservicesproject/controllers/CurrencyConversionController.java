package com.manuel.springmicroservicesproject.controllers;

import com.manuel.springmicroservicesproject.bean.CurrencyExchange;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private Environment environment;
    @GetMapping("/exchange/from/{base}/to/{dest}")
    public CurrencyExchange handleConversion(@PathVariable String base, @PathVariable String dest){
        String port = environment.getProperty("local.server.port");

        CurrencyExchange currencyExchange = new CurrencyExchange(1L, base, dest, BigDecimal.valueOf(100), port);
        return currencyExchange;
    }
}
