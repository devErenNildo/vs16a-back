package com.erenildo.fakebank.auth.service;

import com.erenildo.fakebank.auth.dtos.LoginRequestDTO;
import com.erenildo.fakebank.auth.dtos.LoginResponseDTO;
import com.erenildo.fakebank.auth.entity.Role;
import com.erenildo.fakebank.auth.entity.User;
import com.erenildo.fakebank.auth.exceptions.RegraDeNegocioException;
import com.erenildo.fakebank.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public LoginResponseDTO generateToken(LoginRequestDTO dto) throws Exception {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->  new RegraDeNegocioException("Email ou Senha incorreto !"));

        if (!user.isLoginCorrect(dto, passwordEncoder)) {
            throw new RegraDeNegocioException("Email ou Senha incorreto !");
        }

        var now = Instant.now();
        long expiresIn = 3 * 60 * 60; // 3 horas

        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("fakebank")
                .subject(user.getId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("email", user.getEmail())
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn);
    }
}
