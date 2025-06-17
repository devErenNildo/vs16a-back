package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_gasto_avulso")
public class GastoAvulso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gasto_avulso")
    @SequenceGenerator(name = "seq_gasto_avulso", sequenceName = "seq_gasto_avulso", allocationSize = 1)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate dataGasto;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
