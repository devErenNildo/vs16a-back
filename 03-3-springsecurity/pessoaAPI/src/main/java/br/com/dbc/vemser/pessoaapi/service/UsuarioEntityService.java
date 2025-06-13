package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.dbc.vemser.pessoaapi.repository.UsuarioEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioEntityService {
    private final UsuarioEntityRepository usuarioEntityRepository;

    public Optional<UsuarioEntity> findByLogin(String login) {
        return usuarioEntityRepository.findByLogin(login);
    }
}
