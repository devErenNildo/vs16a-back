package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.cartao.CartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.CartaoResponseDTO;
import com.erenildo.muitaconta.dtos.cartao.EditarCartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.ListagemCartaoResponseDTO;
import com.erenildo.muitaconta.service.CartaoCreditoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    private final CartaoCreditoService cartaoCreditoService;

    public CartaoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @GetMapping
    public Page<ListagemCartaoResponseDTO> listarCartoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return cartaoCreditoService.listarCartoes(page, size);
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDTO> adicionarCartao(@RequestBody CartaoRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoCreditoService.adicionarNovoCartaoCredito(dto));
    }

    @PutMapping("/{idCartao}")
    public ResponseEntity<CartaoResponseDTO> atualizarCartao(@PathVariable("idCartao") Long idCartao, @RequestBody EditarCartaoRequestDTO dto) throws Exception {
        return ResponseEntity.ok(cartaoCreditoService.editarCartaoCredito(idCartao, dto));
    }

    @DeleteMapping("/{idCartao}")
    public ResponseEntity<CartaoResponseDTO> deletarCartao(@PathVariable Long idCartao) throws Exception {
        return ResponseEntity.ok(cartaoCreditoService.removerCartaoCredito(idCartao));
    }
}
