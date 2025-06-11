package com.erenildo.fakebank.account.controller;

import com.erenildo.fakebank.account.dtos.relatorios.RelatorioContaSemMovimentacaoDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioTransactionDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioUserAccountDTO;
import com.erenildo.fakebank.account.service.RelatorioAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/relatorio")
@RequiredArgsConstructor
public class RelatorioAccountController {

    private final RelatorioAccountService relatorioAccountService;

    @GetMapping("/transacoes")
    public Page<RelatorioTransactionDTO> relatorioTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return relatorioAccountService.relatorioDeTransacao(page, size);
    }

    @GetMapping("/user/account")
    public Page<RelatorioUserAccountDTO> relatorioUserAccount(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return relatorioAccountService.relatorioUserAccount(page, size);
    }

    @GetMapping("/user/sem-movimentacao")
    public Page<RelatorioContaSemMovimentacaoDTO> relatorioUserSemMovimentacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return relatorioAccountService.relatorioContaSemMovimentacao(page, size);
    }
}
