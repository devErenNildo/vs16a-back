package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.cartao.CartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.CartaoResponseDTO;
import com.erenildo.muitaconta.service.CartaoCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    private final CartaoCreditoService cartaoCreditoService;

    public CartaoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDTO> adicionarCartao(@RequestBody CartaoRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoCreditoService.adicionarNovoCartaoCredito(dto));
    }
}
