package com.erenildo.fakebank.user.clients;

import com.erenildo.fakebank.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountClient {
    @PostMapping("account/create")
    void createAccount(@RequestBody User user);
}
