package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEndereco;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEnderecoID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoID> {
    List<PessoaEndereco> findByPessoaIdPessoa(Integer pessoa);
}
