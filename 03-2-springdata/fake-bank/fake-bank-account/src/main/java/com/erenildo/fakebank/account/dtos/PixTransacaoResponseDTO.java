package com.erenildo.fakebank.account.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixTransacaoResponseDTO {
    private String msg;
    private String nome;
    private BigDecimal valor;
    private String chavePix;
}
