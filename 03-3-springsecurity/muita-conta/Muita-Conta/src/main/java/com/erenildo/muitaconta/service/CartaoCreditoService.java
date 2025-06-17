package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.cartao.CartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.CartaoResponseDTO;
import com.erenildo.muitaconta.dtos.cartao.EditarCartaoRequestDTO;
import com.erenildo.muitaconta.dtos.cartao.ListagemCartaoResponseDTO;
import com.erenildo.muitaconta.entity.CartaoCredito;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.repository.CartaoCreditoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


    public Page<ListagemCartaoResponseDTO> listarCartoes(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return cartaoCreditoRepository.listarCartoes(pageable);
    }

    public CartaoResponseDTO adicionarNovoCartaoCredito(CartaoRequestDTO dto) throws Exception{
        User userCartao = loginService.buscarUserLogago();

        CartaoCredito cartaoCredito = objectMapper.convertValue(dto, CartaoCredito.class);
        cartaoCredito.setUser(userCartao);
        cartaoCreditoRepository.save(cartaoCredito);

        return new CartaoResponseDTO(cartaoCredito.getId(), "Cartão criado com sucesso");
    }

    public CartaoResponseDTO editarCartaoCredito(Long idCartao, EditarCartaoRequestDTO dto) throws Exception{
        User userCartao = loginService.buscarUserLogago();

        CartaoCredito cartaoCredito = cartaoCreditoRepository.findById(idCartao)
                .orElseThrow(() -> new RegraDeNegocioException("Cartão não encontrado"));

        if(!cartaoCredito.getUser().equals(userCartao))
            throw new RegraDeNegocioException("Cartão não encontrado");

        cartaoCredito = objectMapper.convertValue(dto, CartaoCredito.class);

        cartaoCreditoRepository.save(cartaoCredito);

        return new CartaoResponseDTO(idCartao, "Cartão editado com sucesso");
    }

    public CartaoResponseDTO removerCartaoCredito(Long id) throws Exception {
        User userCartao = loginService.buscarUserLogago();

        CartaoCredito cartaoCredito = cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Cartão não encontrado"));

        if(!cartaoCredito.getUser().equals(userCartao))
            throw new RegraDeNegocioException("Cartão não encontrado");

        cartaoCreditoRepository.delete(cartaoCredito);

        return new CartaoResponseDTO(id, "Cartão removido com sucesso");
    }

    protected YearMonth calcularCompetenciaFatura(LocalDate dataCompra, int fechamento){
        if (dataCompra.getDayOfMonth() <= fechamento) {
            return YearMonth.from(dataCompra);
        } else {
            return YearMonth.from(dataCompra.plusMonths(1));
        }
    }
}
