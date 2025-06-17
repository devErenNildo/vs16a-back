package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.entity.Cargo;
import com.erenildo.muitaconta.entity.Login;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.repository.CargosRepository;
import com.erenildo.muitaconta.repository.LoginRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final CargosRepository cargosRepository;

    public LoginService(
            LoginRepository loginRepository,
            PasswordEncoder passwordEncoder,
            CargosRepository cargosRepository
    ) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
        this.cargosRepository = cargosRepository;
    }

    public Optional<Login> findByEmail(String email) {
        return loginRepository.findByLogin(email);
    }

    public void criarLogin(User user, String senha) throws Exception {

        Cargo cargoUser = cargosRepository.findByIdCargo(2)
                .orElseThrow(() -> new RegraDeNegocioException("Cargo user não encontrado"));

        Login login = new Login();
        login.setLogin(user.getEmail());
        login.setSenha(passwordEncoder.encode(senha));
        login.setId(UUID.randomUUID().toString().replace("-", ""));
        login.setUser(user);
        login.setCargos(Set.of(cargoUser));
        loginRepository.save(login);
    }

    protected User buscarUserLogago() throws Exception {
        Login login = buscarLoginPorId(getIdUSerLogado());
        return returnUserLogin(login);
    }

    private String getIdUSerLogado() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Login buscarLoginPorId(String id) throws Exception{
        return loginRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
    }

    private User returnUserLogin(Login login) {
        return login.getUser();
    }

}
