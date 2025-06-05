package com.erenildo.fakebank.controller;

import com.erenildo.fakebank.dtos.TransacaoRequestDTO;
import com.erenildo.fakebank.dtos.TransacaoResponseDTO;
import com.erenildo.fakebank.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final PixService pixService;

    @PostMapping("/pix/enviar")
    public ResponseEntity<TransacaoResponseDTO> realizarPix (@RequestBody TransacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pixService.realizarTransacaoPix(dto));
    }
}
