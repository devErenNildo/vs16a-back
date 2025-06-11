package com.erenildo.fakebank.auth.controller;

import com.erenildo.fakebank.auth.dtos.LoginRequestDTO;
import com.erenildo.fakebank.auth.dtos.LoginResponseDTO;
import com.erenildo.fakebank.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) throws Exception {
        return ResponseEntity.ok(tokenService.generateToken(dto));
    }
}
