package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_cartao_credito")
public class CartaoCredito {

    @Id
    @Column(name = "id_cartao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cartao_credito")
    @SequenceGenerator(name = "seq_cartao_credito", sequenceName = "seq_cartao_credito", allocationSize = 1)
    private Long id;

    @Column(name = "nome_cartao")
    private String nome;

    private BigDecimal limite;

    private Integer diaFechamentoFatura;

    private Integer diaVencimentoFatura;

    @ManyToOne
    @JoinColumn(name = "id_conta_bancaria")
    private ContaBancaria contaBancaria;

    // Getter Setter

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

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
}
