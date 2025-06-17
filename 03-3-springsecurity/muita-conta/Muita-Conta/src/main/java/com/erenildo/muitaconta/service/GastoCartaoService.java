package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.cartao.GastoCartaoRequestDTO;
import com.erenildo.muitaconta.entity.CartaoCredito;
import com.erenildo.muitaconta.entity.GastoCartao;
import com.erenildo.muitaconta.entity.ParcelaCartao;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.repository.CartaoCreditoRepository;
import com.erenildo.muitaconta.repository.GastoCartaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class GastoCartaoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final CartaoCreditoService cartaoCreditoService;
    private final GastoCartaoRepository gastoCartaoRepository;
    private final ObjectMapper objectMapper;

    public GastoCartaoService(
            CartaoCreditoRepository cartaoCreditoRepository,
            CartaoCreditoService cartaoCreditoService,
            ObjectMapper objectMapper,
            GastoCartaoRepository gastoCartaoRepository
    ) {
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.cartaoCreditoService = cartaoCreditoService;
        this.objectMapper = objectMapper;
        this.gastoCartaoRepository = gastoCartaoRepository;
    }

    public void adicionarCompraParcelada(Long idCartao, GastoCartaoRequestDTO dto) throws Exception {
        CartaoCredito cartaoCredito = cartaoCreditoRepository.findById(idCartao)
                .orElseThrow(() -> new RegraDeNegocioException("Cartão não encontrado, verifique os seus cartões"));

        YearMonth competenciaPrimeiraParcela = cartaoCreditoService.calcularCompetenciaFatura(
                dto.getDataCompra(), cartaoCredito.getDiaFechamentoFatura()
        );

        GastoCartao gasto = new GastoCartao();
        gasto.setDescricao(dto.getDescricao());
        gasto.setValorTotal(dto.getValor());
        gasto.setQuantidadeParcelas(dto.getQuantidadeParcelas());
        gasto.setDataCompra(dto.getDataCompra());
        gasto.setCartaoCredito(cartaoCredito);

        BigDecimal valorParcela = dto.getValor().divide(BigDecimal.valueOf(dto.getQuantidadeParcelas()), 2, RoundingMode.HALF_UP);
        List<ParcelaCartao> parcelaCartao = new ArrayList<>();


        for (int i = 0; i < dto.getQuantidadeParcelas(); i++) {
            ParcelaCartao parcela = new ParcelaCartao();
            parcela.setValorParcela(valorParcela);
            parcela.setCompetencia(competenciaPrimeiraParcela.plusMonths(i));
            parcela.setGastoCartao(gasto);
            parcelaCartao.add(parcela);
        }

        gasto.setParcelaCartao(parcelaCartao);
        gastoCartaoRepository.save(gasto);
    }
}
