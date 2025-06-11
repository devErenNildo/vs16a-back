package com.erenildo.fakebank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FakeBankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeBankAccountApplication.class, args);
    }

}
