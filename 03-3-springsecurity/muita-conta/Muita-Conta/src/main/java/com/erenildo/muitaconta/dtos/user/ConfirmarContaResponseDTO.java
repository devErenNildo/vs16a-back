package com.erenildo.muitaconta.dtos.user;

public class ConfirmarContaResponseDTO {
    private String email;
    private String message;

    // constructor
    public ConfirmarContaResponseDTO() {

    }

    public ConfirmarContaResponseDTO(String email, String message) {
        this.email = email;
        this.message = message;
    }

    // Getter Setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
