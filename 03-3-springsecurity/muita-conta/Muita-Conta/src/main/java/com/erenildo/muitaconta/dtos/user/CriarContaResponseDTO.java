package com.erenildo.muitaconta.dtos.user;

public class CriarContaResponseDTO {
    private String mensagem;

    // Constructor
    public CriarContaResponseDTO() {
    }

    public CriarContaResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    // Getter Setter


    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
