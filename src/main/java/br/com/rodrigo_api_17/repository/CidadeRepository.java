package br.com.rodrigo_api_17.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo_api_17.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    
    List<Cidade> findByNomeAndAtivo(String nome, Boolean status);
}
