package com.erenildo.fakebank.account.dtos.relatorios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioTransactionDTO {
    private String fullName;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String descricao;
}
