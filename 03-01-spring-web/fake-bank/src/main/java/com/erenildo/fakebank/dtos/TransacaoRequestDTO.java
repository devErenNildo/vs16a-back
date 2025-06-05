package com.erenildo.fakebank.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequestDTO {
    private String pixDestinatario;
    private BigDecimal valor;
    private String descricao;
}
