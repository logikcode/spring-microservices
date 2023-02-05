package com.manuel.currencyconversion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@Retry(name = "sample-api", fallbackMethod= "hardcodedResponse")
//@CircuitBreaker(name = "default", fallbackMethod="hardcodedResponse")
//@RateLimiter(name = "default") // no of call per sec to this api

public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    public String sampleApi(){
        logger.info("sample Api call invoked");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
        return responseEntity.getBody();
    }
    public String hardcodedResponse (Exception exception){

        return "fallback-response";
    }
}
