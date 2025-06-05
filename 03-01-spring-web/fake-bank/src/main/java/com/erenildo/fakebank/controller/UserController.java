package com.erenildo.fakebank.controller;

import com.erenildo.fakebank.dtos.AtualizarUserDTO;
import com.erenildo.fakebank.dtos.CadastrarPixRequestDTO;
import com.erenildo.fakebank.dtos.MsgResponseDefaltDTO;
import com.erenildo.fakebank.service.PixService;
import com.erenildo.fakebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PixService pixService;
    private final UserService userService;

    @PostMapping("/register-pix")
    public ResponseEntity<MsgResponseDefaltDTO> createUser(@RequestBody CadastrarPixRequestDTO dto) {
        return ResponseEntity.ok(pixService.cadastrarPix(dto));
    }

    @PutMapping("/atualizar/senha")
    public ResponseEntity<MsgResponseDefaltDTO> updateUser(@RequestBody AtualizarUserDTO dto) {
        return ResponseEntity.ok(userService.atualizarSenha(dto));
    }
}
