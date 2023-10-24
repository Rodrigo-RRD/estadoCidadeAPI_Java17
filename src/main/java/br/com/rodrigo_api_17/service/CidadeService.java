package br.com.rodrigo_api_17.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.rodrigo_api_17.model.Cidade;
import br.com.rodrigo_api_17.repository.CidadeRepository;
import br.com.rodrigo_api_17.repository.EstadoRepository;

import java.util.List;
import java.util.Optional;

@Service
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
    public ResponseEntity<?> salvarVarios(List <Cidade> cidade){
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

}
