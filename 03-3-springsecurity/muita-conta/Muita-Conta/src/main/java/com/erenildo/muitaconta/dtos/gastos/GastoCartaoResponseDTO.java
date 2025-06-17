package com.erenildo.muitaconta.dtos.gastos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoCartaoResponseDTO {
    private String descricao;
    private BigDecimal valorTotal;
    private int quantidadeParcelas;
    private LocalDate dataCompra;

    public GastoCartaoResponseDTO(
            String descricao,
            BigDecimal valorTotal,
            int quantidadeParcelas,
            LocalDate dataCompra
    ) {
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.quantidadeParcelas = quantidadeParcelas;
        this.dataCompra = dataCompra;
    }

    //Getter Setter
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}
