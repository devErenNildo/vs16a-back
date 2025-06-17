package com.erenildo.muitaconta.dtos.gastos;

public class GastosAvulsosResponseDTO {
    private String message;

    //Constructor
    public GastosAvulsosResponseDTO(String message) {
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
