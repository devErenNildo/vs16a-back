package com.erenildo.fakebank.dtos;

import com.erenildo.fakebank.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDTO {

    private String nomeDestinatario;
    private String chavePixDestino;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String descricao;

    public TransactionHistoryDTO(String nomeDestinatario, String chavePixDestino, BigDecimal valor, LocalDateTime dataHora, String descricao) {
        this.nomeDestinatario = nomeDestinatario;
        this.chavePixDestino = chavePixDestino;
        this.valor = valor;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }
}
