package com.erenildo.fakebank.controller;

import com.erenildo.fakebank.dtos.CadastrarPixRequestDTO;
import com.erenildo.fakebank.dtos.MsgResponseDefaltDTO;
import com.erenildo.fakebank.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PixService pixService;

    @PostMapping("/register-pix")
    public ResponseEntity<MsgResponseDefaltDTO> createUser(@RequestBody CadastrarPixRequestDTO dto) {
        return ResponseEntity.ok(pixService.cadastrarPix(dto));
    }
}
