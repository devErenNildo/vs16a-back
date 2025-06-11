package com.erenildo.fakebank.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateAccountDTO {
    private String id;

    private String email;

    private String senha;
}
