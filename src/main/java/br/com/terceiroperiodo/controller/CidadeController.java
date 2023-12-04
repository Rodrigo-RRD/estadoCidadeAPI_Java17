package br.com.terceiroperiodo.controller;

import br.com.terceiroperiodo.model.Cidade;
import br.com.terceiroperiodo.repository.CidadeRepository;
import br.com.terceiroperiodo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @Autowired
    CidadeRepository cidadeRepository;

    /**
     * @param cidade
     * @return ResponseEntity<Cidade>
     */
    @PostMapping(path = "/savecidade")
    public ResponseEntity<Cidade> salvarCidade(@RequestBody Cidade cidade) {

        Cidade response = cidadeService.salvar(cidade);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveVarios")
    public ResponseEntity<Cidade> saveVarios(@RequestBody List<Cidade> cidade) {
        cidadeService.salvarVarios(cidade);
        return ResponseEntity.ok().body(null);
    }

    /**
     * @param cidade
     * @return ResponseEntity<List<Cidade>>
     */
    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Cidade>> buscarTodos() {// Respos vai retornar uma reposta desse objeto

        List<Cidade> response = cidadeService.buscarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<Cidade> update(@RequestBody Cidade cidade) {

        if (!cidadeService.buscarPorId(cidade.getId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cidadeService.salvar(cidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return cidadeService.deleteById(id);
    }

    @GetMapping(path = "/pageable/all", produces = "application/json")
    public ResponseEntity<Page<Cidade>> buscarTodosPaginado(@RequestParam Integer page, @RequestParam Integer size){

        Page<Cidade> response = cidadeService.getAllCidadesByPage(page, size);
        return ResponseEntity.ok(response);
    }
}
