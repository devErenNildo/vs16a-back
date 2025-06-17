package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.cartao.GastoCartaoRequestDTO;
import com.erenildo.muitaconta.service.GastoCartaoService;
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
    public String adicionarCompraParcelada(@PathVariable Long idCartao, @RequestBody @Valid  GastoCartaoRequestDTO dto) throws Exception {
        System.out.printf("Entrou");
        gastoCartaoService.adicionarCompraParcelada(idCartao, dto);
        return "Ok";
    }
}
