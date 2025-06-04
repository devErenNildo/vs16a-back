package com.erenildo.fakebank.exception;

public class EmailCadastradoException extends RuntimeException{
    public EmailCadastradoException(String email) {
        super("O e-mail '" + email + "' já está cadastrado.");
    }
}
