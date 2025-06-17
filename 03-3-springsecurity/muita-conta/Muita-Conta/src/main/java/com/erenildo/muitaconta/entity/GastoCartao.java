package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_gstos_cartao")
public class GastoCartao {

    @Id
    @Column(name = "id_gasto_cartao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gasto_cartao")
    @SequenceGenerator(name = "seq_gasto_cartao", sequenceName = "seq_gasto_cartao", allocationSize = 1)
    private Long id;

    private String descricao;

    private BigDecimal valorTotal;

    private int quantidadeParcelas;

    private LocalDate dataCompra;

    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private CartaoCredito cartaoCredito;

    @OneToMany(mappedBy = "gastoCartao", cascade = CascadeType.ALL)
    private List<ParcelaCartao> parcelaCartao;

    // Getter Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public List<ParcelaCartao> getParcelaCartao() {
        return parcelaCartao;
    }

    public void setParcelaCartao(List<ParcelaCartao> parcelaCartao) {
        this.parcelaCartao = parcelaCartao;
    }
}
