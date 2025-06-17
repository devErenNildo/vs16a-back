package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.user.ListagemUserResonseDTO;
import com.erenildo.muitaconta.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<ListagemUserResonseDTO> lisarUser(
            Integer page,
            Integer size,
            String ordenacao,
            String nome,
            LocalDateTime dataMin,
            LocalDateTime dataMax
    ) {

        if (dataMin == null) {
            dataMin = LocalDateTime.of(1970, 1, 1, 0, 0);
        }
        if (dataMax == null) {
            dataMax = LocalDateTime.of(3000, 1, 1, 0, 0);
        }
        if (nome == null) {
            nome = "";
        }
        Sort sort;

        if ("dataAsc".equalsIgnoreCase(ordenacao)) {
            sort = Sort.by(Sort.Direction.ASC, "dataCadastro");
        } else if ("dataDesc".equalsIgnoreCase(ordenacao)) {
            sort = Sort.by(Sort.Direction.DESC, "dataCadastro");
        } else if ("nomeAsc".equalsIgnoreCase(ordenacao)) {
            sort = Sort.by(Sort.Direction.ASC, "nome");
        } else if ("nomeDesc".equalsIgnoreCase(ordenacao)) {
            sort = Sort.by(Sort.Direction.DESC, "nome");
        } else {
            sort = Sort.by(Sort.Direction.ASC, "nome");
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.listarUsusarios(nome, dataMin, dataMax, pageable);
    }

}
