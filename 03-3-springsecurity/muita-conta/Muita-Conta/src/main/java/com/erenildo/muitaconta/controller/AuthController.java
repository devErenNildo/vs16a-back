package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.token.TokenLoginRequestDTO;
import com.erenildo.muitaconta.dtos.token.TokenLoginResponseDTO;
import com.erenildo.muitaconta.dtos.user.ConfirmarContaRequestDTO;
import com.erenildo.muitaconta.dtos.user.ConfirmarContaResponseDTO;
import com.erenildo.muitaconta.dtos.user.CriarContaRequestDTO;
import com.erenildo.muitaconta.dtos.user.CriarContaResponseDTO;
import com.erenildo.muitaconta.entity.Login;
import com.erenildo.muitaconta.security.TokenService;
import com.erenildo.muitaconta.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(
            UserService userService,
            TokenService tokenService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenLoginResponseDTO> login (@RequestBody @Valid TokenLoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());

        Authentication authentication =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Login loginValido = (Login) authentication.getPrincipal();

        return ResponseEntity.ok(new TokenLoginResponseDTO(tokenService.generateToken(loginValido)));

    }

    @PostMapping("/create")
    public ResponseEntity<CriarContaResponseDTO> criarConta( @RequestBody CriarContaRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.criarConta(dto));
    }

    @PostMapping("/confirm")
    public ResponseEntity<ConfirmarContaResponseDTO> confirmarConta( @RequestBody ConfirmarContaRequestDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.confirmarConta(dto));
    }
}
