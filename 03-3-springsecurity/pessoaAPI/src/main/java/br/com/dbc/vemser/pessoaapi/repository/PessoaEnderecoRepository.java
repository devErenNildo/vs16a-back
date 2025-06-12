package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEndereco;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEnderecoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoID> {
    List<PessoaEndereco> findByPessoaIdPessoa(Integer pessoa);

    @Query("SELECT pe.endereco FROM PESSOA_X_ENDERECO pe WHERE pe.pessoa.idPessoa = :idPessoa")
    List<Endereco> buscarEnderecosPorIdPessoa(@Param("idPessoa") Integer idPessoa);

    @Query("SELECT DISTINCT pe.pessoa FROM PESSOA_X_ENDERECO pe")
    List<Pessoa> buscarPessoasComEndereco();

    @Query(value = "SELECT * FROM PESSOA p WHERE p.id_pessoa NOT IN (SELECT id_pessoa FROM pessoa_x_endereco)", nativeQuery = true)
    List<Pessoa> buscarPessoasSemEndereco();

}
