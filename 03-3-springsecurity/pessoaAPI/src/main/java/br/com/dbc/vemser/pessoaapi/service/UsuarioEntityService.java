package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.UsuarioDTO;
import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.UsuarioEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioEntityService {
    private final UsuarioEntityRepository usuarioEntityRepository;
    private final ObjectMapper objectMapper;

    public Optional<UsuarioEntity> findByLogin(String login) {
        return usuarioEntityRepository.findByLogin(login);
    }

    public UsuarioDTO buscarUserLogado() throws Exception {
        UsuarioEntity usuario = findById(getIdLoggedUser());
        return returnDTO(usuario);
    }

    private Integer getIdLoggedUser() {
        String findUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Integer.parseInt(findUserId);
    }

    private UsuarioEntity findById(Integer id) throws Exception {
        return usuarioEntityRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
    }

    private UsuarioDTO returnDTO(UsuarioEntity usuarioEntity) {
        return objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
    }
}
