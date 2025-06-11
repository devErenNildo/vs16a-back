package com.erenildo.fakebank.api.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class GatewayHeaderFilter {

    @Value("${secrete.header}")
    private String secrete;

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest originalRequest = exchange.getRequest();
            ServerHttpRequest modifiedRequest = originalRequest.mutate()
                    .headers(headers -> headers.add("X-GATEWAY-AUTHORIZED", secrete))
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }
}
