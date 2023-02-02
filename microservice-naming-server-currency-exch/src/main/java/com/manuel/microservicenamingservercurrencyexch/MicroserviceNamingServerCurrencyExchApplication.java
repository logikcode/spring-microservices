package com.manuel.microservicenamingservercurrencyexch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviceNamingServerCurrencyExchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceNamingServerCurrencyExchApplication.class, args);
    }

}
