package com.erenildo.muitaconta.dtos.cartao;

import java.math.BigDecimal;

public class ListagemCartaoResponseDTO {
    private Long id;

    private String nome;

    private BigDecimal limite;

    private Integer diaFechamentoFatura;

    private Integer diaVencimentoFatura;

    //Constructor
    public ListagemCartaoResponseDTO() {
    }

    public ListagemCartaoResponseDTO(Long id, String nome, BigDecimal limite, Integer diaFechamentoFatura, Integer diaVencimentoFatura) {
        this.id = id;
        this.nome = nome;
        this.limite = limite;
        this.diaFechamentoFatura = diaFechamentoFatura;
        this.diaVencimentoFatura = diaVencimentoFatura;
    }

    //Getter Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
