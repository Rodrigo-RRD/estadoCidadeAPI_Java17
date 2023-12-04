package br.com.terceiroperiodo.service;

import br.com.terceiroperiodo.model.Cidade;
import br.com.terceiroperiodo.repository.CidadeRepository;
import br.com.terceiroperiodo.repository.EstadoRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    /**
     * @param cidade
     * @return Cidade
     */
    public Cidade salvar(Cidade cidade) {
        cidade.setEstado(estadoRepository.findById(cidade.getEstado().getId()).get());
        return cidadeRepository.save(cidade);
    }

    /**
     * @param cidade
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> salvarVarios(List<Cidade> cidade) {
        cidadeRepository.saveAll(cidade);
        return ResponseEntity.ok().body(null);
    }

    public List<Cidade> buscarTodos() {
        List<Cidade> response = cidadeRepository.findAll();
        response.removeIf(cidade -> !cidade.getAtivo());
        return response;
    }

    public Optional<Cidade> buscarPorId(Long id) {
        return cidadeRepository.findById(id);
    }

    public List<Cidade> buscarPorNome(String nome) {
        return cidadeRepository.findByNomeAndAtivo(nome, true);
    }

    public ResponseEntity<?> deleteById(Long id) {
        Optional<Cidade> response = buscarPorId(id);
        if (!response.isPresent()) { // se esta presente
            return ResponseEntity.notFound().build();
        }
        response.get().setAtivo(false);
        return ResponseEntity.ok(cidadeRepository.save(response.get()));
    }

    public Page<Cidade> getAllCidadesByPage(Integer page, Integer size) {
        Pageable currentPage = PageRequest.of(page, size);
        Page<Cidade> response = cidadeRepository.findAll(currentPage);
        log.info("getAllCidadesByPage() - size: <{}>, context: <{}>", response.getSize(), response.getContent());
        return response;
    }
}
