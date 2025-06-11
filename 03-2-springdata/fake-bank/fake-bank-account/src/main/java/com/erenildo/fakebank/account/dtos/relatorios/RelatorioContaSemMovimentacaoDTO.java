package com.erenildo.fakebank.account.dtos.relatorios;


public class RelatorioContaSemMovimentacaoDTO extends RelatorioUserAccountDTO {
    public RelatorioContaSemMovimentacaoDTO(String fullName, String agencia, String numeroConta, String chavePix) {
        super(fullName, agencia, numeroConta, chavePix);
    }
}
