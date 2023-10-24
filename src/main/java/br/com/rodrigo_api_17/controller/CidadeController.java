package br.com.rodrigo_api_17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.rodrigo_api_17.model.Cidade;
import br.com.rodrigo_api_17.service.CidadeService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // disponivel a todos
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    /**
     * @param cidade
     * @return ResponseEntity<Cidade>
     */
    @PostMapping(path = "/saveCidade", produces = "application/json")
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

}
