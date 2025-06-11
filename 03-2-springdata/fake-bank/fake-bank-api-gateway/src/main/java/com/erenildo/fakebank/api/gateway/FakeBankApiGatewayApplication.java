package com.erenildo.fakebank.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FakeBankApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeBankApiGatewayApplication.class, args);
    }

}
