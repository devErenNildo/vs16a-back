package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosRequestDTO;
import com.erenildo.muitaconta.dtos.gastos.GastosAvulsosResponseDTO;
import com.erenildo.muitaconta.dtos.gastos.ListagemGastoAvulsoResponseDTO;
import com.erenildo.muitaconta.entity.GastoAvulso;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.repository.GastoAvulsoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    public Page<ListagemGastoAvulsoResponseDTO> listarGastos(
            Integer page,
            Integer size,
            String ordenacao, LocalDate dataMin, LocalDate dataMax
    ) throws Exception {
        User user = loginService.buscarUserLogago();

        Sort sort;
        if ("valorDesc".equalsIgnoreCase(ordenacao)) {
            sort = Sort.by(Sort.Direction.DESC, "valor");
        } else if ("valorAsc".equalsIgnoreCase(ordenacao)) {
            sort = Sort.by(Sort.Direction.ASC, "valor");
        } else {
            sort = Sort.unsorted();  // Sem ordenação
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return gastoAvulsoRepository.listarGastosAvulso(user.getId(), dataMin, dataMax, pageable);
    }

    public GastosAvulsosResponseDTO apagarGasto(Long id) throws Exception {
        User user = loginService.buscarUserLogago();

        GastoAvulso gastoAvulso = gastoAvulsoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("GAsto não encontrado"));

        if (!gastoAvulso.getUser().equals(user)) {
            throw new RegraDeNegocioException("GAsto não encontrado");
        }

        gastoAvulsoRepository.delete(gastoAvulso);

        return new GastosAvulsosResponseDTO("Gastos apagado com sucesso!");
    }

}
