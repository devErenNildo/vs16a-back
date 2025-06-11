package com.erenildo.fakebank.message.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageDTO {
    private String destino;
    private String cogigo;
    private String nome;
}
