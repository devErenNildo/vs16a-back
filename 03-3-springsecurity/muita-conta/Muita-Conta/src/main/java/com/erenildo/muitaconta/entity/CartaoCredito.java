package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @OneToMany(mappedBy = "cartaoCredito", cascade = CascadeType.ALL)
    private List<GastoCartao> gastos;

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

    public List<GastoCartao> getGastos() {
        return gastos;
    }

    public void setGastos(List<GastoCartao> gastos) {
        this.gastos = gastos;
    }
}
