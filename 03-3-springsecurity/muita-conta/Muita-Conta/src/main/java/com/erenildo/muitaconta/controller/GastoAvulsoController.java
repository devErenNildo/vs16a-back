package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosRequestDTO;
import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosResponseDTO;
import com.erenildo.muitaconta.dtos.gastos.ListagemGastoAvulsoResponseDTO;
import com.erenildo.muitaconta.service.GastoAvulsoService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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

    @GetMapping
    public Page<ListagemGastoAvulsoResponseDTO> listarGastos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String ordenacao,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataMin,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataMax
    ) throws Exception {
        return gastoAvulsoService.listarGastos(page, size, ordenacao, dataMin, dataMax);
    }

    @DeleteMapping("/{idGasto}")
    public ResponseEntity<GastosAvulsosResponseDTO> apagarGasto(@PathVariable Long idGasto) throws Exception {
        return ResponseEntity.ok(gastoAvulsoService.apagarGasto(idGasto));
    }
}
