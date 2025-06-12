package br.com.dbc.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "CONTATO")
@AllArgsConstructor
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTATO")
    @SequenceGenerator(name = "SEQ_CONTATO", sequenceName = "SEQ_CONTATO", allocationSize = 1)
    @Column(name = "ID_CONTATO")
    private Integer idContato;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA", nullable = false)
    private Pessoa pessoa;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIPO")
    private TipoContato tipoContato;

    @Column(name = "NUMERO", length = 14)
    private String numero;

    @Column(name = "DESCRICAO", length = 100)
    private String descricao;
}
