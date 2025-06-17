package com.erenildo.muitaconta.dtos.gastos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ListagemGastoAvulsoResponseDTO {
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataGasto;


    //Constructor
    public ListagemGastoAvulsoResponseDTO(String descricao, BigDecimal valor, LocalDate dataGasto) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataGasto = dataGasto;
    }

    //Getter Setter
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataGasto() {
        return dataGasto;
    }

    public void setDataGasto(LocalDate dataGasto) {
        this.dataGasto = dataGasto;
    }
}
