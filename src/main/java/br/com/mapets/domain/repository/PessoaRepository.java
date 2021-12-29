package br.com.mapets.domain.repository;

import br.com.mapets.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("select p from Pessoa p where p.nome = :pNome")
    Pessoa findPessoa(@Param("pNome") String nome);

    List<Pessoa> findByNomeContaining(String nome);
}
