package br.com.dbc.vemser.pessoaapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "PET")
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PET_SEQ")
    @SequenceGenerator(name = "PET_SEQ", sequenceName = "seq_pet", allocationSize = 1)
    @Column(name = "id_pet")
    private Integer idPet;

    private String nome;

    private String tipo;


    @OneToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;
}
