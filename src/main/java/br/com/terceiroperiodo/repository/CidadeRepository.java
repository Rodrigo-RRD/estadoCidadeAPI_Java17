package br.com.terceiroperiodo.repository;

import br.com.terceiroperiodo.model.Cidade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByNomeAndAtivo(String nome, Boolean status);

    public Page<Cidade> findAll(Pageable pageable);
}
