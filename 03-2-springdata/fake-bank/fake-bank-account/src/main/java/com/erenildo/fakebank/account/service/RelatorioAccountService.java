package com.erenildo.fakebank.account.service;

import com.erenildo.fakebank.account.dtos.relatorios.RelatorioContaSemMovimentacaoDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioTransactionDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioUserAccountDTO;
import com.erenildo.fakebank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelatorioAccountService {

    private final TransactionRepository transactionRepository;

    public Page<RelatorioTransactionDTO> relatorioDeTransacao(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.relatorioTransactions(pageable);
    }

    public Page<RelatorioUserAccountDTO> relatorioUserAccount(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.relatorioUserAccounts(pageable);
    }

    public Page<RelatorioContaSemMovimentacaoDTO> relatorioContaSemMovimentacao(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.relatorioContaSemMovimentacoes(pageable);
    }
}
