package br.com.rodrigo_api_17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo_api_17.model.Estado;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findByNomeAndAtivo(String nome, Boolean status);

}
