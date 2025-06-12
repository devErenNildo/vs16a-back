package com.erenildo.fakebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class TransacaoResponseDTO {
    private String msg;
    private String nome;
    private BigDecimal valor;
    private String chavePix;

    public TransacaoResponseDTO() {
    }

    public TransacaoResponseDTO(String msg, String nome, BigDecimal valor, String chavePix) {
        this.msg = msg;
        this.nome = nome;
        this.valor = valor;
        this.chavePix = chavePix;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}
