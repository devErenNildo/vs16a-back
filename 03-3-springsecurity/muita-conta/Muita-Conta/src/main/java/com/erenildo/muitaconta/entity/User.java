package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @Column(name = "id_user")
    private String id;

    private String nome;

    private String email;

    private LocalDateTime dataCadastro;

    private Boolean contaConfirmada = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartaoCredito> cartoes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ContaMensal> contasMensais;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Boleto> boletos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GastoAvulso> gastoAvulsos;


    // Getter Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<ContaMensal> getContasMensais() {
        return contasMensais;
    }

    public void setContasMensais(List<ContaMensal> contasMensais) {
        this.contasMensais = contasMensais;
    }

    public Boolean getContaConfirmada() {
        return contaConfirmada;
    }

    public void setContaConfirmada(Boolean contaConfirmada) {
        this.contaConfirmada = contaConfirmada;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public List<GastoAvulso> getGastoAvulsos() {
        return gastoAvulsos;
    }

    public void setGastoAvulsos(List<GastoAvulso> gastoAvulsos) {
        this.gastoAvulsos = gastoAvulsos;
    }

    public List<CartaoCredito> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoCredito> cartoes) {
        this.cartoes = cartoes;
    }
}
