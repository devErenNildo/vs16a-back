package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "tb_parcela_cartao")
public class ParcelaCartao {

    @Id
    @Column(name = "id_parcela_cartao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parcela_cartao")
    @SequenceGenerator(name = "seq_parcela_cartao", sequenceName = "seq_parcela_cartao", allocationSize = 1)
    private Long id;

    private BigDecimal valorParcela;
    private YearMonth competencia;

    @ManyToOne
    @JoinColumn(name = "id_gasto_cartao")
    private GastoCartao gastoCartao;

    // Getter Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public YearMonth getCompetencia() {
        return competencia;
    }

    public void setCompetencia(YearMonth competencia) {
        this.competencia = competencia;
    }

    public GastoCartao getGastoCartao() {
        return gastoCartao;
    }

    public void setGastoCartao(GastoCartao gastoCartao) {
        this.gastoCartao = gastoCartao;
    }
}
