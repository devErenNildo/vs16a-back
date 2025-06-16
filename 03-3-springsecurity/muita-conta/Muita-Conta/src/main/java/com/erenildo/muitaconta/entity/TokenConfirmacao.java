package com.erenildo.muitaconta.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_token_confirmacao")
public class TokenConfirmacao {

    @Id
    @Column(name = "id_token_confirmacao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_token_confirmacao")
    @SequenceGenerator(name = "seq_token_confirmacao", sequenceName = "seq_token_confirmacao", allocationSize = 1)
    private Long id;

    private String codigo;


    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    private LocalDateTime expiracao;

    // Getter Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(LocalDateTime expiracao) {
        this.expiracao = expiracao;
    }
}
