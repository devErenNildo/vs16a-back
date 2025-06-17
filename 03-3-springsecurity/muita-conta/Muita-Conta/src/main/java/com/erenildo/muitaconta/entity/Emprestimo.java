package com.erenildo.muitaconta.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {

    @Id
    @Column(name = "id_emprestimo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_emprestimo")
    @SequenceGenerator(name = "seq_emprestimo", sequenceName = "seq_emprestimo", allocationSize = 1)
    private Long id;

    private String descricao;

    private BigDecimal valorTotal;

    private BigDecimal valorParcela;
    private int quantidadeParcelas;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    // Getter Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
