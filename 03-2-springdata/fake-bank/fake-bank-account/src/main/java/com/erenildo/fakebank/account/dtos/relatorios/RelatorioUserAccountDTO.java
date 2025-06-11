package com.erenildo.fakebank.account.dtos.relatorios;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioUserAccountDTO {
    private String fullName;
    private String agencia;
    private String numeroConta;
    private String chavePix;
}
