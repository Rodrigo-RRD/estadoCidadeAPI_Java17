package br.com.rodrigo_api_17.controller;

import br.com.rodrigo_api_17.entities.ProdutoEntities;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Produto {

    @GetMapping(path = "/produtos", produces = "application/json")/*produce é para definir que é json*/
    public ProdutoEntities getProduto(){
        ProdutoEntities p = new ProdutoEntities();
        p.setId(1l);
        p.setNome("nome");
        p.setPreco(8.99);
        return p;
    }
    @GetMapping(path = "/produtos/{produtoId}")
    public String findById(@PathVariable Long produtoId){
        if(produtoId==1){
            return "produto1";
        }
        return "Produto não encontrado";
    }



}
