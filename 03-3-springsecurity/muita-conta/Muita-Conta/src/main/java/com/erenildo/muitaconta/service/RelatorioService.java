package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.relatorios.RelatorioDividasMesDTO;
import com.erenildo.muitaconta.repository.UserRepository;
import com.erenildo.muitaconta.security.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class RelatorioService {

    private final UserRepository userRepository;
    private final LoginService loginService;

    public RelatorioService(
            UserRepository userRepository,
            LoginService loginService
    ) {
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

    public RelatorioDividasMesDTO getRelatorioMesAtual() throws Exception {

        String idUser = loginService.buscarUserLogago().getId();
        LocalDate hoje = LocalDate.now();
        LocalDate inicioDoMes = hoje.withDayOfMonth(1);
        LocalDate fimDoMes = hoje.withDayOfMonth(hoje.lengthOfMonth());
        YearMonth competencia = YearMonth.from(hoje);

        return userRepository.getRelatorioDividasMesAtual(idUser, competencia, inicioDoMes, fimDoMes);
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
}
