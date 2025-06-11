package com.erenildo.fakebank.auth.clients;

import com.erenildo.fakebank.auth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("user/get-cpf/{cpf}")
    User getUserByCpf(
            @PathVariable("cpf") String cpf
    );
}
