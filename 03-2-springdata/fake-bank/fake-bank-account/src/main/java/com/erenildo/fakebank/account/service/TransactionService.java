package com.erenildo.fakebank.account.service;

import com.erenildo.fakebank.account.dtos.PixTransactionHistoryDTO;
import com.erenildo.fakebank.account.exceptions.RegraDeNegocioException;
import com.erenildo.fakebank.account.repository.AccountRepository;
import com.erenildo.fakebank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Page<PixTransactionHistoryDTO> buscarPixRecebidos(Integer page, Integer size, String idUser) throws Exception {
        accountRepository.findByUsuarioId(idUser)
                .orElseThrow(() -> new RegraDeNegocioException("Conta de origem não encontrada"));

        Pageable pageable = PageRequest.of(page, size);

        return transactionRepository.buscarPixRecebidos(idUser, pageable);
    }

    public Page<PixTransactionHistoryDTO> buscarPixEnviados(Integer page, Integer size, String idUser) throws Exception {
        accountRepository.findByUsuarioId(idUser)
                .orElseThrow(() -> new RegraDeNegocioException("Conta de origem não encontrada"));

        Pageable pageable = PageRequest.of(page, size);

        return transactionRepository.buscarPixEnviados(idUser, pageable);
    }
}
