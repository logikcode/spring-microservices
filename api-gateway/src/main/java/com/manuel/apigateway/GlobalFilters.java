package com.manuel.apigateway;

import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Named
public class GlobalFilters implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(GlobalFilters.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Request entry path is -> {}", exchange.getRequest().getPath());

        return chain.filter(exchange);
    }
}
