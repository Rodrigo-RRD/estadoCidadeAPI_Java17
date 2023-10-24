package br.com.rodrigo_api_17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.rodrigo_api_17.model.Cidade;
import br.com.rodrigo_api_17.model.Estado;
import br.com.rodrigo_api_17.service.EstadoService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    /**
     * @param estado
     * @return ResponseEntity<Estado>
     */
    @PostMapping()
    public ResponseEntity<Estado> salvarEstado(@RequestBody Estado estado) {

        Estado response = estadoService.salvar(estado);
        return ResponseEntity.ok(response);
    }

    /**
     * @return ResponseEntity<List<Estado>>
     */
    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Estado>> buscarTodos() {

        List<Estado> response = estadoService.buscarTodos();
        return ResponseEntity.ok(response);
    }

    /*
     * @PathVariable é uma anotação usada para mapear uma parte da URL de uma
     * solicitação HTTP para um parâmetro de método em um controlador. Essa anotação
     * é frequentemente usada em controladores RESTful para extrair valores de
     * variáveis ​​de caminho (path variables) das URLs das solicitações.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {

        Optional<Estado> response = estadoService.buscarPorId(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<List<Estado>> buscarPorNome(@PathVariable String nome) {

        List<Estado> response = estadoService.buscarPorNome(nome);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    /*
     * @RequestBody é uma anotação usada para indicar que um parâmetro de método de
     * um controlador deve ser vinculado ao corpo (body) da solicitação HTTP.
     * Geralmente, é usado para receber dados enviados pelo cliente em uma
     * solicitação POST, PUT ou PATCH
     */
    @PutMapping()
    public ResponseEntity<Estado> update(@RequestBody Estado estado) {

        if (!estadoService.buscarPorId(estado.getId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoService.atualizar(estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return estadoService.deleteById(id);
    }

    @PostMapping("/saveVarios")
    public ResponseEntity<Cidade> saveVarios(@RequestBody Estado estado) {
        estadoService.salvar(estado);
        return ResponseEntity.ok().body(null);
    }

}
