package com.yeshiw.gallary.springcloudgatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
public class Gatewayconfig {
	
	@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
        		.route(r -> r
                		.path("/rest/gallery/**")
                        .filters(f -> 
                        	f.hystrix(config -> config.setName("gallery-service")
                				.setFallbackUri("forward:/fallback/message")))
                        .uri("lb://GALLERY-SERVICE/")
                        .id("gallery-service"))
        		
                .route(r -> r
                		.path("/rest/mongodb/**")
                        .filters(f -> f.hystrix(config -> config.setName("image-service")
                				.setFallbackUri("forward:/fallback/message")))
                        .uri("lb://IMAGE-SERVICE/")
                        .id("image-service"))
                .build();
    }

}
