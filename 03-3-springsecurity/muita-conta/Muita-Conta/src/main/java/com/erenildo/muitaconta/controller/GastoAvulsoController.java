package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosRequestDTO;
import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosResponseDTO;
import com.erenildo.muitaconta.service.GastoAvulsoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("gastos/avulsos")
public class GastoAvulsoController {

    private final GastoAvulsoService gastoAvulsoService;

    public GastoAvulsoController(GastoAvulsoService gastoAvulsoService) {
        this.gastoAvulsoService = gastoAvulsoService;
    }

    @PostMapping
    public ResponseEntity<GastosAvulsosResponseDTO> novoGasto(@RequestBody @Valid GastosAvulsosRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoAvulsoService.adicionarGastos(dto));
    }
}
