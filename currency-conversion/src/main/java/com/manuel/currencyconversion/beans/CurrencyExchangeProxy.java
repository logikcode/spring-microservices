package com.manuel.currencyconversion.beans;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange", url="localhost:8000")
/*removing the explicit url = "localhost:8000" to enable load balancing be handled automatically */
//@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{base}/to/{dest}")
     CurrencyConversion retrieveExchangeValue(@PathVariable String base,
                                                  @PathVariable String dest);
    }
