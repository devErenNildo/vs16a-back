package com.erenildo.fakebank.dtos;

import lombok.Data;

@Data
public class ConfirmAccountRequestDTO {
    private String token;
    private String email;
}
