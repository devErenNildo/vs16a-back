package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioEntityService {
    private final UsuarioEntityService usuarioEntityService;

    public Optional<UsuarioEntity> findByLogin(String login) {
        return usuarioEntityService.findByLogin(login);
    }
}
