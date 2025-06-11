package com.erenildo.fakebank.user.controller;

import com.erenildo.fakebank.user.dtos.*;
import com.erenildo.fakebank.user.kafka.Producers;
import com.erenildo.fakebank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Producers producers;

    @PostMapping("/create")
    public ResponseEntity<UserCreateAccontResponseDTO> createUser(@RequestBody UserCreateAccountDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
    }

    @PostMapping("/confirm")
    public ResponseEntity<ConfirmAccountResponseDTO> confirmUser(@RequestBody ConfirmAccountRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.confirmUser(dto));
    }
}
