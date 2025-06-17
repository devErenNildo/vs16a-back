package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.cartao.GastoCartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.GastoCartaoResponseDTO;
import com.erenildo.muitaconta.service.GastoCartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/gastos/cartao")
public class GastoCartaoController {

    private final GastoCartaoService gastoCartaoService;

    public GastoCartaoController(
            GastoCartaoService gastoCartaoService
    ) {
        this.gastoCartaoService = gastoCartaoService;
    }

    @PostMapping("/{idCartao}")
    public ResponseEntity<GastoCartaoResponseDTO> adicionarCompraParcelada(@PathVariable Long idCartao, @RequestBody @Valid  GastoCartaoRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoCartaoService.adicionarCompraParcelada(idCartao, dto));
    }

    @DeleteMapping("/{idCartao}")
    public ResponseEntity<GastoCartaoResponseDTO> apagarCompra(@PathVariable Long idCartao) throws Exception {
        return ResponseEntity.ok(gastoCartaoService.apagarGasto(idCartao));
    }
}
