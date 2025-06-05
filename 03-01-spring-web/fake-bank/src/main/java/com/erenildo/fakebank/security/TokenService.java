package com.erenildo.fakebank.security;


import com.erenildo.fakebank.dtos.LoginRequestDTO;
import com.erenildo.fakebank.dtos.LoginResponseDTO;
import com.erenildo.fakebank.entity.Role;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO generateToken(LoginRequestDTO dto) {

        Optional<User> user = userRepository.findByCpf(dto.getCpf());

        if (user.isEmpty() || !user.get().isLoginCorrect(dto, passwordEncoder)) {
            throw new BadCredentialsException("cpf ou senha incorretos!");
        }
        var now = Instant.now();
        long expiresIn = 3 * 60 * 60; // 3 horas

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("fakebank")
                .subject(user.get().getId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("email", user.get().getEmail())
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn);
    }
}
