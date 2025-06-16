package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.entity.Login;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.repository.LoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(
            LoginRepository loginRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Login> findByEmail(String email) {
        return loginRepository.findByLogin(email);
    }

    public void criarLogin(User user, String senha) {

        Login login = new Login();
        login.setLogin(user.getEmail());
        login.setSenha(passwordEncoder.encode(senha));
        login.setId(UUID.randomUUID().toString().replace("-", ""));
        login.setUser(user);
        loginRepository.save(login);
    }

}
