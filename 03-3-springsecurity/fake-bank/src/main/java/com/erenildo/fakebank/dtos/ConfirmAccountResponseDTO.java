package com.erenildo.fakebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmAccountResponseDTO {
    private String email;
    private String msg;

    public ConfirmAccountResponseDTO(String email, String msg) {
        this.email = email;
        this.msg = msg;
    }
}
