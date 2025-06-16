package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_conta_bancaria")
public class ContaBancaria {

    @Id
    @Column(name = "id_conta_bancaria")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_bancaria")
    @SequenceGenerator(name = "seq_conta_bancaria", sequenceName = "seq_conta_bancaria", allocationSize = 1)
    private Long id;

    private String nomeBanco;

    @OneToMany(mappedBy = "contaBancaria", cascade = CascadeType.ALL)
    private List<CartaoCredito> cartaoCreditos;

    @OneToMany(mappedBy = "contaBancaria", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartaoCredito> getCartaoCreditos() {
        return cartaoCreditos;
    }

    public void setCartaoCreditos(List<CartaoCredito> cartaoCreditos) {
        this.cartaoCreditos = cartaoCreditos;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
