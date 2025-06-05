package com.erenildo.fakebank.service;

import com.erenildo.fakebank.dtos.CadastrarPixRequestDTO;
import com.erenildo.fakebank.dtos.MsgResponseDefaltDTO;
import com.erenildo.fakebank.dtos.TransacaoRequestDTO;
import com.erenildo.fakebank.dtos.TransacaoResponseDTO;
import com.erenildo.fakebank.entity.Account;
import com.erenildo.fakebank.entity.Transaction;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.exception.RegraDeNegocioRuntimeExpeptions;
import com.erenildo.fakebank.repository.AccountRepository;
import com.erenildo.fakebank.repository.TrasactionRepository;
import com.erenildo.fakebank.security.TokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PixService {

    private final AccountRepository accountRepository;
    private final TrasactionRepository trasactionRepository;
    private final TokenUtil tokenUtil;

    @Transactional
    public TransacaoResponseDTO realizarTransacaoPix(TransacaoRequestDTO dto) {

        String idUsuarioOrigem = tokenUtil.getUserIdFromToken();

        Account origem = accountRepository.findByUsuarioId(idUsuarioOrigem)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Conta origem não encontrada."));

        Account destino = accountRepository.findByChavePix(dto.getPixDestinatario())
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Chave pix não encontrada ou inexistente."));

        if (origem.getId().equals(destino.getId())) {
            throw new RegraDeNegocioRuntimeExpeptions("Não é possível transferir para a mesma conta.");
        }

        if (origem.getSaldo().compareTo(dto.getValor()) < 0) {
            throw new RegraDeNegocioRuntimeExpeptions("Saldo insuficiente.");
        }

        BigDecimal taxa = dto.getValor().multiply(BigDecimal.valueOf(0.15));
        BigDecimal valorLiquido = dto.getValor().subtract(taxa);

        origem.setSaldo(origem.getSaldo().subtract(dto.getValor()));
        destino.setSaldo(destino.getSaldo().add(valorLiquido));
        accountRepository.save(origem);
        accountRepository.save(destino);

        Transaction transacao = new Transaction();
        transacao.setContaOrigem(origem);
        transacao.setContaDestino(destino);
        transacao.setValor(dto.getValor());
        transacao.setDescricao(dto.getDescricao());
        transacao.setDataHora(LocalDateTime.now());

        trasactionRepository.save(transacao);

        return new TransacaoResponseDTO("Pix realizado com sucesso", destino.getUsuario().getFullName(), dto.getValor(), destino.getChavePix());

    }

    public MsgResponseDefaltDTO cadastrarPix(CadastrarPixRequestDTO dto) {
        String idUsuarioOrigem = tokenUtil.getUserIdFromToken();

        Account conta = accountRepository.findByUsuarioId(idUsuarioOrigem)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Conta não encontrada."));

        if (conta.getChavePix() != null ) {
            throw new RegraDeNegocioRuntimeExpeptions("Essa conta já possui uma chave PIX.");
        }

        if ( accountRepository.existsByChavePix(dto.getChavePix()) ) {
            throw new RegraDeNegocioRuntimeExpeptions("Essa chave PIX já está em uso por outra conta.");
        }

        User usuario = conta.getUsuario();

        boolean chaveValida = dto.getChavePix().equalsIgnoreCase(usuario.getEmail())
                || dto.getChavePix().equals(usuario.getCpf())
                || dto.getChavePix().equals(usuario.getCelular());

        if (!chaveValida) {
            throw new RegraDeNegocioRuntimeExpeptions("A chave PIX deve ser seu email, CPF ou celular.");
        }

        conta.setChavePix(dto.getChavePix());
        accountRepository.save(conta);

        return new MsgResponseDefaltDTO("Chave pix: " + dto.getChavePix() + " cadastrada com sucesso.");
    }
}
