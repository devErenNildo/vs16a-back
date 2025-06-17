package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.cartao.CartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.CartaoResponseDTO;
import com.erenildo.muitaconta.entity.CartaoCredito;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.repository.CartaoCreditoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class CartaoCreditoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final ObjectMapper objectMapper;
    private final LoginService loginService;

    public CartaoCreditoService(
            CartaoCreditoRepository cartaoCreditoRepository,
            ObjectMapper objectMapper,
            LoginService loginService
    ) {
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.objectMapper = objectMapper;
        this.loginService = loginService;
    }

    public CartaoResponseDTO adicionarNovoCartaoCredito(CartaoRequestDTO dto) throws Exception{
        User userCartao = loginService.buscarUserLogago();

        CartaoCredito cartaoCredito = objectMapper.convertValue(dto, CartaoCredito.class);
        cartaoCredito.setUser(userCartao);
        cartaoCreditoRepository.save(cartaoCredito);

        return new CartaoResponseDTO(cartaoCredito.getId(), "Cartão criado com sucesso");
    }

    protected YearMonth calcularCompetenciaFatura(LocalDate dataCompra, int fechamento){
        if (dataCompra.getDayOfMonth() <= fechamento) {
            return YearMonth.from(dataCompra);
        } else {
            return YearMonth.from(dataCompra.plusMonths(1));
        }
    }
}
