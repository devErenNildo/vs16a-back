package com.erenildo.fakebank.service;

import com.erenildo.fakebank.entity.PessoaEntity;
import com.erenildo.fakebank.repository.PessoaEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioEntityService {

    private final PessoaEntityRepository pessoaEntityRepository;

    public UsuarioEntityService(PessoaEntityRepository pessoaEntityRepositor) {
        this.pessoaEntityRepository = pessoaEntityRepositor;
    }

    public Optional<PessoaEntity> findByLogin(String login) {
        return pessoaEntityRepository.findByLogin(login);
    }
}
