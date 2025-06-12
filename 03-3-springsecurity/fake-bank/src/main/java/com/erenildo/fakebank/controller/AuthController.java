package com.erenildo.fakebank.controller;

import com.erenildo.fakebank.dtos.*;
import com.erenildo.fakebank.service.TokenConfirmationService;
import com.erenildo.fakebank.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Validated
public class AuthController {
    private final UserService userService;
    private final TokenConfirmationService tokenConfirmationService;
    public final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, TokenConfirmationService tokenConfirmationService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenConfirmationService = tokenConfirmationService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAccountResponseDTO> createAccount (@RequestBody @Valid UserCreateAccountDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
    }

    @PostMapping("/confirm_account")
    public ResponseEntity<ConfirmAccountResponseDTO> confirmAccount (@RequestBody @Valid ConfirmAccountRequestDTO dto) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.confirmUser(dto.getEmail(), dto.getToken()));
    }

    @PostMapping("/resend-token")
    public ResponseEntity<CreateAccountResponseDTO> resendToken (@RequestBody @Valid ResendTokenRequestDTO email) {
        tokenConfirmationService.reenviarToken(email);
        return ResponseEntity.ok(new CreateAccountResponseDTO("Token reenviado com sucesso"));
    }

    @PostMapping
    public ResponseEntity login (@RequestBody @Valid LoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return ResponseEntity.ok().build();
    }

}
