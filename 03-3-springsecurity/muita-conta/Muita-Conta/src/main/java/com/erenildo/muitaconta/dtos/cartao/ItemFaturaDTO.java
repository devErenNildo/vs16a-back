package com.erenildo.muitaconta.dtos.cartao;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ItemFaturaDTO {
    private String descricaoCompra;
    private BigDecimal valorParcela;
    private YearMonth competencia;

    public ItemFaturaDTO(
            String descricaoCompra,
            BigDecimal valorParcela,
            YearMonth competencia
    ) {
        this.descricaoCompra = descricaoCompra;
        this.valorParcela = valorParcela;
        this.competencia = competencia;
    }

    //Getter Setter

    public String getDescricaoCompra() {
        return descricaoCompra;
    }

    public void setDescricaoCompra(String descricaoCompra) {
        this.descricaoCompra = descricaoCompra;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public YearMonth getCompetencia() {
        return competencia;
    }

    public void setCompetencia(YearMonth competencia) {
        this.competencia = competencia;
    }

}
