package com.erenildo.muitaconta.dtos.cartao;

public class CartaoResponseDTO {
    private Long idCartao;
    private String message;

    //Constructor
    public CartaoResponseDTO() {}

    public CartaoResponseDTO(Long idCartao, String message) {
        this.idCartao = idCartao;
        this.message = message;
    }

    //Getter Setter
    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
