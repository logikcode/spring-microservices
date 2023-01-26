package com.manuel.springmicroservicesproject.controllers;

import com.manuel.springmicroservicesproject.bean.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    @Autowired
    private Environment environment;
    @GetMapping("/exchange/from/{base}/to/{des}")
    public CurrencyExchange handleConversion(@PathVariable String base, @PathVariable String des){
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = new CurrencyExchange("1", base, des, BigDecimal.valueOf(100), port);
        return currencyExchange;
    }
}
