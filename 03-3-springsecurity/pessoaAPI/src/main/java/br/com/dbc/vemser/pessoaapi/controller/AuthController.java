package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.LoginDTO;
import br.com.dbc.vemser.pessoaapi.dtos.UsuarioDTO;
import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.dbc.vemser.pessoaapi.security.TokenService;
import br.com.dbc.vemser.pessoaapi.service.UsuarioEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;
    private final UsuarioEntityService usuarioEntityService;

    public AuthController(@Lazy AuthenticationManager authenticationManager, TokenService tokenService, UsuarioEntityService usuarioEntityService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioEntityService = usuarioEntityService;
    }

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha());


        Authentication authentication =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UsuarioEntity usuarioValidado = (UsuarioEntity) authentication.getPrincipal();

        return ResponseEntity.ok(tokenService.generateToken(usuarioValidado));
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> usuarioLogado() throws Exception {
        return ResponseEntity.ok(usuarioEntityService.buscarUserLogado());
    }
}
