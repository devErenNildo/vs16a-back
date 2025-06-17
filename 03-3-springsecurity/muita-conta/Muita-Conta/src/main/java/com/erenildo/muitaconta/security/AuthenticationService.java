package com.erenildo.muitaconta.security;

import com.erenildo.muitaconta.entity.Login;
import com.erenildo.muitaconta.service.LoginService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final LoginService loginService;

    public AuthenticationService(LoginService loginService) {
        this.loginService = loginService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Login> loginOptional = loginService.findByEmail(username);

        return loginOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inválido"));
    }
}
