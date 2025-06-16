package com.erenildo.muitaconta.dtos.token;

public class TokenLoginResponseDTO {
    private String token;

    // Constructor
    public TokenLoginResponseDTO(String token) {
        this.token = token;
    }

    // Getter Setter

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
