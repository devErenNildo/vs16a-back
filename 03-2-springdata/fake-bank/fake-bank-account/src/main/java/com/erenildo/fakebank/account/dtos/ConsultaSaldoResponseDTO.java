package com.erenildo.fakebank.account.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaSaldoResponseDTO {
    private String nome;
    private BigDecimal saldo;
}
