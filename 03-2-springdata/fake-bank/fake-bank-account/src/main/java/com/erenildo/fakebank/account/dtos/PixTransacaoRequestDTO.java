package com.erenildo.fakebank.account.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PixTransacaoRequestDTO {
    private String pixDestinatario;
    private BigDecimal valor;
    private String descricao;
}
