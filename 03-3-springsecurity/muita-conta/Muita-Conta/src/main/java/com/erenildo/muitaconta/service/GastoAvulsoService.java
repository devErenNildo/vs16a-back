package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosRequestDTO;
import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosResponseDTO;
import com.erenildo.muitaconta.entity.GastoAvulso;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.repository.GastoAvulsoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class GastoAvulsoService {

    private final GastoAvulsoRepository gastoAvulsoRepository;
    private final LoginService loginService;
    private final ObjectMapper objectMapper;

    public GastoAvulsoService(
            GastoAvulsoRepository gastoAvulsoRepository,
            LoginService loginService,
            ObjectMapper objectMapper

    ) {
        this.gastoAvulsoRepository = gastoAvulsoRepository;
        this.loginService = loginService;
        this.objectMapper = objectMapper;
    }

    public GastosAvulsosResponseDTO adicionarGastos(GastosAvulsosRequestDTO dto) throws Exception {
        User user = loginService.buscarUserLogago();

        GastoAvulso gastoAvulso = objectMapper.convertValue(dto, GastoAvulso.class);
        gastoAvulso.setUser(user);

        gastoAvulsoRepository.save(gastoAvulso);

        return new GastosAvulsosResponseDTO("Gastos adicionado com sucesso!");
    }
}
