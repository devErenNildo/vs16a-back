package com.erenildo.fakebank.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FakeBankUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeBankUserApplication.class, args);
    }

}
