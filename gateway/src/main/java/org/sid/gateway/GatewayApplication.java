package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
 //   @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/students/**").uri("http://localhost:8081"))
                .route(r->r.path("/courses/**").uri("http://localhost:8082"))
                .route(r->r.path("/professors/**").uri("http://localhost:8083"))
                .build();
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator dynamiqueRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp)
        {
            return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
        }

}
