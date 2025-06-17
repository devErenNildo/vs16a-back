package com.erenildo.muitaconta.dtos.cartao;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CartaoRequestDTO {
    @NotBlank(message = "O nome do cartão é obrigatório.")
    private String nome;

    @NotNull(message = "O limite do cartão é obrigatório.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O limite deve ser maior que zero.")
    private BigDecimal limite;

    @NotNull(message = "O dia de fechamento da fatura é obrigatório.")
    @Min(value = 1, message = "O dia de fechamento da fatura deve ser entre 1 e 31.")
    @Max(value = 31, message = "O dia de fechamento da fatura deve ser entre 1 e 31.")
    private Integer diaFechamentoFatura;


    @NotNull(message = "O dia de vencimento da fatura é obrigatório.")
    @Min(value = 1, message = "O dia de vencimento da fatura deve ser entre 1 e 31.")
    @Max(value = 31, message = "O dia de vencimento da fatura deve ser entre 1 e 31.")
    private Integer diaVencimentoFatura;

    // Getter Setter

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Integer getDiaFechamentoFatura() {
        return diaFechamentoFatura;
    }

    public void setDiaFechamentoFatura(Integer diaFechamentoFatura) {
        this.diaFechamentoFatura = diaFechamentoFatura;
    }

    public Integer getDiaVencimentoFatura() {
        return diaVencimentoFatura;
    }

    public void setDiaVencimentoFatura(Integer diaVencimentoFatura) {
        this.diaVencimentoFatura = diaVencimentoFatura;
    }
}
