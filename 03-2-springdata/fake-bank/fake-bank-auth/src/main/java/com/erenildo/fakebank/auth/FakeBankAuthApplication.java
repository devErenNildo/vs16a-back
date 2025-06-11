package com.erenildo.fakebank.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FakeBankAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeBankAuthApplication.class, args);
    }

}
