package com.erenildo.fakebank.account.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixTransactionHistoryDTO {
    private String nomeDestinatario;
    private String chavePixDestino;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String descricao;
}
