package com.erenildo.fakebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaSaldoResponseDTO {

    BigDecimal saldo;

    public ConsultaSaldoResponseDTO(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
