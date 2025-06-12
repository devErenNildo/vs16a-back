package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query("SELECT e FROM ENDERECO e WHERE e.pais = :pais")
    List<Endereco> buscarEnderecosPorPais(@Param("pais") String pais);

    @Query(value = "SELECT * FROM ENDERECO e WHERE e.cidade = :valor OR e.pais = :valor", nativeQuery = true)
    List<Endereco> buscarEnderecosPorCidadeOuPais(@Param("valor") String valor);

    @Query(value = "SELECT * FROM ENDERECO e WHERE e.complemento IS NULL OR e.complemento = ''", nativeQuery = true)
    List<Endereco> buscarEnderecosSemComplemento();



}
