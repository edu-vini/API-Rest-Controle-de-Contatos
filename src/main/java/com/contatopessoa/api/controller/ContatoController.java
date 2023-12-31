package com.contatopessoa.api.controller;

import com.contatopessoa.api.model.Contato;
import com.contatopessoa.api.model.Pessoa;
import com.contatopessoa.api.service.ContatoService;
import com.contatopessoa.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;
    @Autowired
    private PessoaService pessoaService;
    @PostMapping("/pessoas/{idPessoa}/contatos")
    public Contato saveContato(@RequestBody Contato contato, @PathVariable Integer idPessoa ){

        Pessoa pessoa = pessoaService.findById(idPessoa).get();

        contato.setPessoa(pessoa);

        return contatoService.save(contato);
    }
    @GetMapping("/contatos/{id}")
    public Contato findContato(@PathVariable Integer id){
        return contatoService.findById(id).get();
    }
    @GetMapping("/pessoas/{idPessoa}/contatos")
    public List<Contato> findPessoaContatos(@PathVariable Integer idPessoa){
        return contatoService.findByPessoaId(idPessoa);
    }
    @PutMapping("/contatos/{id}")
    public Contato updateContato(@RequestBody Contato contato, @PathVariable Integer id){
        Contato contatoBd = contatoService.findById(id).get();

        Integer tipo = contato.getTipo();
        Long numeroContato = contato.getContato();

        if(tipo != null){
            contatoBd.setTipo(tipo);
        }
        if(numeroContato != null) {
            contatoBd.setContato(numeroContato);
        }

        return contatoService.save(contatoBd);
    }
    @DeleteMapping("/contatos/{id}")
    public Contato deleteContato(@PathVariable Integer id){
        return contatoService.deleteById(id);
    }
}
