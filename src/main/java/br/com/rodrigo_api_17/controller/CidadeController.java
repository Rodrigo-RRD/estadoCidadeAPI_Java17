package br.com.rodrigo_api_17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.rodrigo_api_17.model.Cidade;
import br.com.rodrigo_api_17.service.CidadeService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") //disponivel a todos
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @PostMapping()
    public ResponseEntity<Cidade> salvarCidade(@RequestBody Cidade cidade){

        Cidade response = cidadeService.salvar(cidade);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Cidade>> buscarTodos(){//Respos vai retornar uma reposta desse objeto

        List<Cidade> response = cidadeService.buscarTodos();
        return ResponseEntity.ok(response);
    }

}
