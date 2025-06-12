package com.erenildo.fakebank.security;

import com.erenildo.fakebank.entity.PessoaEntity;
import com.erenildo.fakebank.service.UsuarioEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsuarioEntityService usuarioEntityService;

    public AuthenticationService(UsuarioEntityService usuarioEntityService) {
        this.usuarioEntityService = usuarioEntityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<PessoaEntity> usuarioEntityOptional = usuarioEntityService.findByLogin(username);

        return usuarioEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inválido"));
    }
}
