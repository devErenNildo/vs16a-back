package com.erenildo.muitaconta.dtos.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ConfirmarContaRequestDTO {

    @NotBlank(message = "O token é obrigatório")
    private String token;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    // Getter Setter

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
