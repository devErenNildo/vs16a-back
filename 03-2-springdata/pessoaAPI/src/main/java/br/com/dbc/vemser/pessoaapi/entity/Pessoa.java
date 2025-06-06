package br.com.dbc.vemser.pessoaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @NotNull
    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "EMAIL", length = 255, nullable = false)
    private String email;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos;
}
