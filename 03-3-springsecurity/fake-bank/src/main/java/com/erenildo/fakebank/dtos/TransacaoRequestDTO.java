package com.erenildo.fakebank.dtos;

import lombok.Data;

import java.math.BigDecimal;

public class TransacaoRequestDTO {
    private String pixDestinatario;
    private BigDecimal valor;
    private String descricao;

    public String getPixDestinatario() {
        return pixDestinatario;
    }

    public void setPixDestinatario(String pixDestinatario) {
        this.pixDestinatario = pixDestinatario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
