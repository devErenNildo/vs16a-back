package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaCompletaResponseDTO {
    private Integer idPessoa;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;

    private Pet pet;
    private Set<Contato> contatos;
    private List<Endereco> enderecos;
}
