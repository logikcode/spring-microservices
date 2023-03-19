package com.manuel.currencyconversion.controllers;

import com.manuel.currencyconversion.beans.CurrencyConversion;
import com.manuel.currencyconversion.beans.CurrencyExchangeProxy;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private Environment environment;
    private CurrencyExchangeProxy exchangeProxy;

    @GetMapping("/conversion/from/{base}/to/{dest}/quantity/{qty}")
    public CurrencyConversion handleConversion(@PathVariable String base, @PathVariable String dest, @PathVariable BigDecimal qty){
        String port = environment.getProperty("local.server.port");
        BigDecimal convertMultiple ;
        BigDecimal totalCalculated ;

        CurrencyConversion currencyConversion = exchangeProxy.retrieveExchangeValue(base, dest);
        convertMultiple = currencyConversion.getConversionMultiples();
        totalCalculated = convertMultiple.multiply(qty);

        CurrencyConversion conv = new CurrencyConversion(currencyConversion.getId(), base, dest, qty,
                convertMultiple, totalCalculated, currencyConversion.getEnvironment() + " "+ "rest template");

        return conv;
    }
    /*
    This part demonstrate using feign framework in handling the api call to another
    microservice called currency exchange.
    Step:
    i. include the spring-cloud-openfeign dependency
    ii. create a proxy interface with the endpoint of the service to call.
    iii. annotate spring-application with @EnableFeignClients
    iv. annotate the interface with @FeignClient
     */
    @GetMapping("/conversion-with-feign/from/{base}/to/{dest}/quantity/{qty}")
    public CurrencyConversion handleConversionWithFeign(@PathVariable String base, @PathVariable String dest, @PathVariable BigDecimal qty){
        String port = environment.getProperty("local.server.port");
        BigDecimal convertMultiple ;
        BigDecimal totalCalculated ;

        Map<String , String> uriVariables = new HashMap<>();
        uriVariables.put("base", base);
        uriVariables.put("dest", dest);


        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().
                getForEntity("http://localhost:8000/currency-exchange/from/{base}/to/{dest})",
                        CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        convertMultiple = currencyConversion.getConversionMultiples();
        totalCalculated = convertMultiple.multiply(qty);

        CurrencyConversion conv = new CurrencyConversion(currencyConversion.getId(), base, dest, qty,
                convertMultiple, totalCalculated, currencyConversion.getEnvironment() + " "+ "feign");

        return conv;
    }
}
