package com.erenildo.fakebank.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateAccountKafkaDTO {
    private String id;

    private String email;

    private String senha;
}
