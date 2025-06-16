package com.erenildo.muitaconta.dtos.token;

import javax.validation.constraints.NotNull;

public class TokenLoginRequestDTO {

    @NotNull(message = "Login não pode ser vazio")
    private String login;

    @NotNull(message = "Senha não pode ser vazia")
    private String senha;

    // Getter Setter


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
