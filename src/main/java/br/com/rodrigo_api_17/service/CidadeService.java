package br.com.rodrigo_api_17.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigo_api_17.model.Cidade;
import br.com.rodrigo_api_17.repository.CidadeRepository;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> buscarTodos(){
        return cidadeRepository.findAll();
    }

}
