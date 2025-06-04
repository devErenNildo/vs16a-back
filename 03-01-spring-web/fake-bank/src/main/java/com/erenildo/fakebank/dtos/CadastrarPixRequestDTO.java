package com.erenildo.fakebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPixRequestDTO {
    private String chavePix;
    private String idUsuario;
}
