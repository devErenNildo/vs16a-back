package com.erenildo.fakebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResponseDTO {
    private String msg;
    private String nome;
    private BigDecimal valor;
    private String chavePix;
}
