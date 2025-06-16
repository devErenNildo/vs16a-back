package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_boleto")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_boleto")
    @SequenceGenerator(name = "seq_boleto", sequenceName = "seq_boleto", allocationSize = 1)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private String tipoServico;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
