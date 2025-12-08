package com.pessoa.controller.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pessoa.controller.dto.request.PessoaRequest;
import com.pessoa.controller.dto.response.PessoaResponse;
import com.pessoa.entity.Pessoa;
import com.pessoa.repository.PessoaRepository;

public class PessoaAdapterController {
        private final PessoaRepository repository;

    public PessoaAdapterController(PessoaRepository repository){
        this.repository = repository;
    }

    public Page<Pessoa> findAll(int page){
        PageRequest pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Pessoa findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Pessoa save(Pessoa person){
        return repository.save(person);
    }

    public Pessoa update(String id, Pessoa pessoa){
        Long rightID = Long.valueOf(id);

        Pessoa existente = repository.findById(rightID).orElse(null);
        if (existente == null) return null;

        existente.setNome(pessoa.getNome());
        existente.setDtNascimento(pessoa.getDtNascimento());
        existente.setAtivo(pessoa.isAtivo());

        return repository.save(existente);
    }

    public void delete(String id){
        Long rightID = Long.valueOf(id);
        repository.deleteById(rightID);
    }

    public static Pessoa toEntity(PessoaRequest request) {
        return new Pessoa(
            request.nome(),
            request.dtNascimento(),
            request.ativo()
        );
    }

    public static PessoaResponse toResponse(Pessoa pessoa) {
        return new PessoaResponse(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getDtNascimento(),
            pessoa.isAtivo()
        );
    }
}
