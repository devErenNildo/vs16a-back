package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.cartao.DetalheFaturaDTO;
import com.erenildo.muitaconta.dtos.gastos.GastoCartaoResponseDTO;
import com.erenildo.muitaconta.dtos.relatorios.RelatorioDividasMesDTO;
import com.erenildo.muitaconta.service.RelatorioService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/mes")
    public ResponseEntity<RelatorioDividasMesDTO> relatorioDividasMes( @RequestParam(required = false) String competencia) throws Exception {
        return ResponseEntity.ok(relatorioService.getRelatorioPorMes(competencia));
    }

    @GetMapping("/cartao")
    public ResponseEntity<DetalheFaturaDTO> relatorioFatura(
            @RequestParam(required = false) Long idCartao,
            @RequestParam(required = false) String competencia
    ) throws Exception {
        return ResponseEntity.ok(relatorioService.listarFaturaDoMes(idCartao, competencia));
    }

    @GetMapping("/cartao/gastos")
    public Page<GastoCartaoResponseDTO> listarGastos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long idCartao
    ) throws Exception {
        return relatorioService.listarGastos(page, size, idCartao);
    }
}
