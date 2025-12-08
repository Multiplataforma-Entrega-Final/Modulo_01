package com.pessoa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.controller.adapter.PessoaAdapterController;
import com.pessoa.controller.dto.request.PessoaRequest;
import com.pessoa.controller.dto.response.PessoaResponse;
import com.pessoa.entity.Pessoa;
import com.pessoa.service.PessoaService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("")
    public Page<Pessoa> listarTudo(@RequestParam int page) {
        return service.listarTudo(page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("path")
    public PessoaResponse salvar(@Valid @RequestBody PessoaRequest request) {
        Pessoa pessoa =  PessoaAdapterController.toEntity(request);
        Pessoa pessoaGerada = service.criar(pessoa);

        return PessoaAdapterController.toResponse(pessoaGerada);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public PessoaResponse update(@PathVariable Long id, @Valid @RequestBody PessoaRequest request) {
        Pessoa pessoa = PessoaAdapterController.toEntity(request);
        Pessoa pessoaUpdate = service.atualizar(id, pessoa);

        return PessoaAdapterController.toResponse(pessoaUpdate);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PessoaResponse findById(@PathVariable Long id){
        Pessoa pessoa = service.pesquisar(id);
        return PessoaAdapterController.toResponse(pessoa);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deletar(id);
    }

}
