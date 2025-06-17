package com.erenildo.muitaconta.messages.dtos;

public class EmailTokenDTO {
    private String destino;
    private String cogigo;
    private String nome;

    // Constructor
    public EmailTokenDTO() {
    }

    public EmailTokenDTO(String destino, String cogigo, String nome) {
        this.destino = destino;
        this.cogigo = cogigo;
        this.nome = nome;
    }

     //Getter Setter

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCogigo() {
        return cogigo;
    }

    public void setCogigo(String cogigo) {
        this.cogigo = cogigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
