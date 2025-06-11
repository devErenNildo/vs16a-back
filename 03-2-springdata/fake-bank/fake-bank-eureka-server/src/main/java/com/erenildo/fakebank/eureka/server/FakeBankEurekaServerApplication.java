package com.erenildo.fakebank.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FakeBankEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeBankEurekaServerApplication.class, args);
    }

}
