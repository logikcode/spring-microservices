package com.manuel.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder locatorBuilder){
        Function<PredicateSpec, Buildable<Route>> routeFunc
                = predicateSpec -> predicateSpec.path("/get")
                .filters(f ->  f.addRequestHeader("customHeader", "myCustomHeader")
                        .addRequestParameter("myParam", "param")
                )
                .uri("http://httpbin.org:80");


        return locatorBuilder.routes().
                route(routeFunc)
                // this means when a request comes in through this path, then redirect
                .route(p -> p.path("/currency-exchange/**")
                        //redirect using naming server + lb: load balancing
                .uri("lb://currency-exchange"))

                .route(p -> p.path("/conversion/**")
                        //redirect using naming server + lb: load balancing
                        .uri("lb://currency-converter"))

                .route(p -> p.path("/conversion-with-feign/**")
                        //redirect using naming server + lb: load balancing
                        .uri("lb://currency-converter"))

                .route(p -> p.path("/conversion-new/**")
                        // handle the replacement of a path with another and calling a service to handle the request
                        .filters(f-> f.rewritePath("/conversion-new/(?<segment>.*)", "conversion-with-feign/${segment}"))
                        //redirect using naming server + lb: load balancing
                        .uri("lb://currency-converter"))

                .build();
    }
}
