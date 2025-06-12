package br.com.dbc.vemser.pessoaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity(name = "PESSOA")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ")
    @SequenceGenerator(name = "PESSOA_SEQ", sequenceName = "seq_pessoa", allocationSize = 1)
    @Column(name = "id_pessoa")
    private Integer idPessoa;


    @Column(name = "NOME", length = 255)
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Pet pet;

    @NotNull
    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "EMAIL", length = 255, nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contato> contatos;

}
