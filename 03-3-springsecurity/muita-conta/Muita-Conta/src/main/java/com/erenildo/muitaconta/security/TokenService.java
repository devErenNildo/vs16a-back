package com.erenildo.muitaconta.security;

import com.erenildo.muitaconta.entity.Cargo;
import com.erenildo.muitaconta.entity.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    private final  String TOKEN_PREFIX = "Bearer";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Login login) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        List<String> cargos = login.getCargos().stream()
                .map(Cargo::getAuthority)
                .toList();

        return TOKEN_PREFIX + " " +
                Jwts.builder()
                        .setIssuer("vemser-api")
                        .claim(Claims.ID, login.getId())
                        .claim("cargos", cargos)
                        .setIssuedAt(now)
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String user = body.get(Claims.ID, String.class);
            if (user != null) {
                List<String> cargos = body.get("cargos", List.class);
                List<SimpleGrantedAuthority> authorities = cargos.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, authorities);
                return usernamePasswordAuthenticationToken;
            }
        }

        return null;
    }
}
