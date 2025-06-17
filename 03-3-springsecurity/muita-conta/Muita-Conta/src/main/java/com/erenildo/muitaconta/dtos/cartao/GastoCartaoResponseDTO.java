package com.erenildo.muitaconta.dtos.cartao;

public class GastoCartaoResponseDTO {
    private String message;

    //Constructor

    public GastoCartaoResponseDTO() {
    }

    public GastoCartaoResponseDTO(String message) {
        this.message = message;
    }

    //Getter Setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
