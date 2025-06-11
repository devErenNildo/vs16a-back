package com.erenildo.fakebank.account.security;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenDecoder {

    public String extractUserId(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(stripBearer(token));
            return jwt.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }

    private String stripBearer(String token) {
        return token.replace("Bearer ", "").trim();
    }
}
