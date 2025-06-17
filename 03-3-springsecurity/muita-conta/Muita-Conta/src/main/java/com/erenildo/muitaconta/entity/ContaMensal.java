package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_conta_mensal")
public class ContaMensal {

    @Id
    @Column(name = "id_conta_mensal")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_mensal")
    @SequenceGenerator(name = "seq_conta_mensal", sequenceName = "seq_conta_mensal", allocationSize = 1)
    private Long id;

    private BigDecimal valor;

    private LocalDate mes;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getMes() {
        return mes;
    }

    public void setMes(LocalDate mes) {
        this.mes = mes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
