package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.dtos.relatorio.FlatRelatorioDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    // query methods
    public List<Pessoa> findAllByCpfContains(String cpf);

    List<Pessoa> findAllByDataNascimentoBetween(LocalDate dtInicial, LocalDate dtFinal);

    @Query("""
    SELECT new br.com.dbc.vemser.pessoaapi.dtos.relatorio.FlatRelatorioDTO(
        p.idPessoa, p.nome, p.email,
        c.numero,
        e.cep, e.cidade, e.estado, e.pais,
        pet.nome
    )
    FROM PESSOA p
    LEFT JOIN p.contatos c
    LEFT JOIN PESSOA_X_ENDERECO pe ON pe.pessoa.idPessoa = p.idPessoa
    LEFT JOIN ENDERECO e ON pe.endereco.idEndereco = e.idEndereco
    LEFT JOIN PET pet ON pet.pessoa.idPessoa = p.idPessoa
    WHERE (:idPessoa IS NULL OR p.idPessoa = :idPessoa)
""")
    List<FlatRelatorioDTO> buscarPessoaRelatorio(@Param("idPessoa") Integer idPessoa);

    @Query("SELECT p FROM PESSOA p WHERE p.dataNascimento BETWEEN :dataInicio AND :dataFim")
    List<Pessoa> buscarPorIntervaloDeNascimento(@Param("dataInicio") LocalDate dataInicio,
                                                @Param("dataFim") LocalDate dataFim);

    // jpql = (java) persistence query language
    @Query(" select p " +
            "  from PESSOA p" +
            " where p.cpf = ?1")
    List<Pessoa> listPessoasByCpf(String cpf);

    // NATIVA
    @Query(value = " select p.* " +
            "         from PESSOA p" +
            "        where p.cpf = :cpf", nativeQuery = true)
    List<Pessoa> listPessoasByCpfNative(@Param("cpf") String cpf);

    // TESTE?
    @Query(" select p " +
            "  from PESSOA p" +
            " where p.cpf = :cpf")
    List<Pessoa> listPessoasByCpfParam(@Param("cpf") String cpf);


    @Query(" select p " +
            "  from PESSOA p " +
            "  join p.contatos cont " +  // ==== "  join CONTATO cont on (p.id_pessoa = c.id_pessoa)" +
            " where cont.tipoContato = :tipoContato ")
    List<Pessoa> listPessoasPorTipoContato(@Param("tipoContato") TipoContato tipoContato);

    @Query(value = " select * " +
            "  from PESSOA p " +
            "  join CONTATO cont on (p.id_pessoa = cont.id_pessoa)" +
            " where cont.tipo = :tipoContato", nativeQuery = true)
    List<Pessoa> listPessoasPorTipoContatoNative(@Param("tipoContato") Integer tipoContato);

//    @Query(value = " select new br.com.pessoaapi.dto.PessoaCompostaDTO(" +
//            " p.batata," +
//            " p.email," +
//            " pety.nome," +
//            " pety.tipo" +
//            ")" +
//            "  from PESSOA p " +
//            "  join p.pet pety " +
//            " where (:idPessoa is null OR p.idPessoa = :idPessoa )")
//    List<PessoaCompostaDTO> listaCompostaDTO(@Param("idPessoa") Integer idPessoa);

    // JPQL PAGINADO
//    @Query(value = """
//           select new br.com.pessoaapi.dto.PessoaCompostaTudoDTO(
//           p.id,
//           p.batata,
//           p.cpf,
//           p.email,
//           c.numero,
//           e.cep,
//           e.cidade,
//           e.estado,
//           e.pais,
//           pet.nome)
//           from PESSOA p
//           left join p.contatos c
//           left join p.enderecos e
//           left join p.pet pet
//           """) // se for uma JPQL
//    Page<PessoaCompostaTudoDTO> findPessoaCompostaTudoPaginado(Pageable pageable);

    //QUERY NATIVA
    @Query(value = " select p.* " +
            "         from PESSOA p" +
            "        where (:cpf is null or p.cpf = :cpf)",
            countQuery = "select count(*) " +
                    "         from PESSOA p" +
                    "        where (:cpf is null or p.cpf = :cpf)",
            nativeQuery = true)
    Page<Pessoa> listPessoasByCpfNativePaginado(@Param("cpf") String cpf, Pageable pageable);

}
