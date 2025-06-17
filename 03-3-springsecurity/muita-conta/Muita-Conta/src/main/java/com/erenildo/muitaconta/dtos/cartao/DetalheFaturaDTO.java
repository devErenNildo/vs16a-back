package com.erenildo.muitaconta.dtos.cartao;

import java.math.BigDecimal;
import java.util.List;

public class DetalheFaturaDTO {
    private BigDecimal totalFatura;
    private List<ItemFaturaDTO> itens;

    public DetalheFaturaDTO(BigDecimal totalFatura, List<ItemFaturaDTO> itens) {
        this.totalFatura = totalFatura;
        this.itens = itens;
    }

    //Getter Setter
    public BigDecimal getTotalFatura() {
        return totalFatura;
    }

    public void setTotalFatura(BigDecimal totalFatura) {
        this.totalFatura = totalFatura;
    }

    public List<ItemFaturaDTO> getParcelas() {
        return itens;
    }

    public void setParcelas(List<ItemFaturaDTO> parcelas) {
        this.itens = parcelas;
    }
}
