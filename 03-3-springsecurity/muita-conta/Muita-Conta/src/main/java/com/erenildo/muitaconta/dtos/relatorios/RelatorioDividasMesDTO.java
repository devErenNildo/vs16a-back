package com.erenildo.muitaconta.dtos.relatorios;

import java.math.BigDecimal;

public class RelatorioDividasMesDTO {
    private BigDecimal totalCartaoCredito;
    private BigDecimal totalBoletos;
    private BigDecimal totalContasMensais;
    private BigDecimal totalGastosAvulsos;
    private BigDecimal totalGeral;

    public RelatorioDividasMesDTO(Number totalCartaoCredito,
                                  Number totalBoletos,
                                  Number totalContasMensais,
                                  Number totalGastosAvulsos) {
        this.totalCartaoCredito = totalCartaoCredito != null ? BigDecimal.valueOf(totalCartaoCredito.doubleValue()) : BigDecimal.ZERO;
        this.totalBoletos = totalBoletos != null ? BigDecimal.valueOf(totalBoletos.doubleValue()) : BigDecimal.ZERO;
        this.totalContasMensais = totalContasMensais != null ? BigDecimal.valueOf(totalContasMensais.doubleValue()) : BigDecimal.ZERO;
        this.totalGastosAvulsos = totalGastosAvulsos != null ? BigDecimal.valueOf(totalGastosAvulsos.doubleValue()) : BigDecimal.ZERO;
        this.totalGeral = this.totalCartaoCredito
                .add(this.totalBoletos)
                .add(this.totalContasMensais)
                .add(this.totalGastosAvulsos);
    }

    //Getter Setter
    public BigDecimal getTotalCartaoCredito() {
        return totalCartaoCredito;
    }

    public void setTotalCartaoCredito(BigDecimal totalCartaoCredito) {
        this.totalCartaoCredito = totalCartaoCredito;
    }

    public BigDecimal getTotalBoletos() {
        return totalBoletos;
    }

    public void setTotalBoletos(BigDecimal totalBoletos) {
        this.totalBoletos = totalBoletos;
    }

    public BigDecimal getTotalContasMensais() {
        return totalContasMensais;
    }

    public void setTotalContasMensais(BigDecimal totalContasMensais) {
        this.totalContasMensais = totalContasMensais;
    }

    public BigDecimal getTotalGastosAvulsos() {
        return totalGastosAvulsos;
    }

    public void setTotalGastosAvulsos(BigDecimal totalGastosAvulsos) {
        this.totalGastosAvulsos = totalGastosAvulsos;
    }

    public BigDecimal getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(BigDecimal totalGeral) {
        this.totalGeral = totalGeral;
    }
}
