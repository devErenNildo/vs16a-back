package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.cartao.DetalheFaturaDTO;
import com.erenildo.muitaconta.dtos.cartao.ItemFaturaDTO;
import com.erenildo.muitaconta.dtos.gastos.GastoCartaoResponseDTO;
import com.erenildo.muitaconta.dtos.relatorios.RelatorioDividasMesDTO;
import com.erenildo.muitaconta.entity.CartaoCredito;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.repository.CartaoCreditoRepository;
import com.erenildo.muitaconta.repository.GastoCartaoRepository;
import com.erenildo.muitaconta.repository.ParcelaCartaoRepository;
import com.erenildo.muitaconta.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class RelatorioService {

    private final UserRepository userRepository;
    private final LoginService loginService;
    private final ParcelaCartaoRepository parcelaCartaoRepository;
    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final GastoCartaoRepository gastoCartaoRepository;

    public RelatorioService(
            UserRepository userRepository,
            LoginService loginService,
            ParcelaCartaoRepository parcelaCartaoRepository,
            CartaoCreditoRepository cartaoCreditoRepository,
            GastoCartaoRepository gastoCartaoRepository
    ) {
        this.userRepository = userRepository;
        this.loginService = loginService;
        this.parcelaCartaoRepository = parcelaCartaoRepository;
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.gastoCartaoRepository = gastoCartaoRepository;
    }

    public DetalheFaturaDTO listarFaturaDoMes(Long idCartao, String competenciaStr) throws Exception {
        User userCartao = loginService.buscarUserLogago();

        if (idCartao != null) {
            CartaoCredito cartaoCredito = cartaoCreditoRepository.findById(idCartao)
                    .orElseThrow(() -> new RegraDeNegocioException("Cartão não encontrado"));

            if(!cartaoCredito.getUser().equals(userCartao))
                throw new RegraDeNegocioException("Cartão não encontrado");
        }

        YearMonth competencia = (competenciaStr == null || competenciaStr.isBlank())
                ? YearMonth.from(LocalDate.now())
                : YearMonth.parse(competenciaStr);

        List<ItemFaturaDTO> itens = parcelaCartaoRepository.buscarParcelasDaFatura(idCartao, competencia);


        BigDecimal total = itens.stream()
                .map(ItemFaturaDTO::getValorParcela)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DetalheFaturaDTO(total, itens);

    }


    public RelatorioDividasMesDTO getRelatorioPorMes(String competenciaStr) throws Exception {

        String idUser = loginService.buscarUserLogago().getId();

        YearMonth competencia;

        if (competenciaStr == null || competenciaStr.isBlank()) {
            competencia = YearMonth.from(LocalDate.now());
        } else {
            competencia = YearMonth.parse(competenciaStr);
        }

        LocalDate inicioDoMes = competencia.atDay(1);
        LocalDate fimDoMes = competencia.atEndOfMonth();

        return userRepository.getRelatorioDividasMesAtual(idUser, competencia, inicioDoMes, fimDoMes);
    }

    public Page<GastoCartaoResponseDTO> listarGastos(Integer page, Integer size, Long idCartao){
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataCompra").descending());
        return gastoCartaoRepository.listarGastosCartao(idCartao, pageable);
    }
}
