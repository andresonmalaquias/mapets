package br.com.mapets.domain.repository;

import br.com.mapets.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("select c from Cidade c where c.nome = :pNome")
    Cidade findCidade(@Param("pNome") String nome);

    List<Cidade> findByNomeContaining(String nome);

    Cidade findByCod(Integer cod);
    Cidade findByNome(String nome);
}
