package com.erenildo.muitaconta.dtos.user;

import java.time.LocalDateTime;

public class ListagemUserResonseDTO {
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;
    private Boolean contaConfirmada;

    //Constructor

    public ListagemUserResonseDTO() {
    }

    public ListagemUserResonseDTO(String nome, String email, LocalDateTime dataCadastro, Boolean contaConfirmada) {
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.contaConfirmada = contaConfirmada;
    }

    //Getter Setter

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

    public Boolean getContaConfirmada() {
        return contaConfirmada;
    }

    public void setContaConfirmada(Boolean contaConfirmada) {
        this.contaConfirmada = contaConfirmada;
    }
}
