package com.candido.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Douglas Candido
 * Classe de roteamento entre as requisições
 * O cliente acessa via porta 8080 e
 * A classe encaminha as requisições para os devidos serviços
 */

@Configuration
public class RoutConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", p -> p
                        .path("/users/**")
                        .filters(f -> f.rewritePath("/users(?<segment>/?.*)", "/api/v1/users${segment}"))
                        .uri("http://localhost:8081"))
                .route("meme", p -> p
                        .path("/memes/**")
                        .filters(f -> f.rewritePath("/memes(?<segment>/?.*)", "/api/v1/memes${segment}"))
                        .uri("http://localhost:8082"))
                .route("category", p -> p
                        .path("/category/**")
                        .filters(f -> f.rewritePath("/category(?<segment>/?.*)", "/api/v1/category${segment}"))
                        .uri("http://localhost:8083"))
                .build();
    }
}



