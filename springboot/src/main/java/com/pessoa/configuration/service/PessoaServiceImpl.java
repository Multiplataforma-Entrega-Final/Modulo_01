package com.pessoa.configuration.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pessoa.entity.Pessoa;
import com.pessoa.repository.PessoaRepository;
import com.pessoa.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa criar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    public Page<Pessoa> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Pessoa pesquisar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    @Override
    public Pessoa atualizar(Long id, Pessoa novaPessoa) {
        Pessoa existente = pesquisar(id);
        existente.setNome(novaPessoa.getNome());
        existente.setDtNascimento(novaPessoa.getDtNascimento());
        existente.setAtivo(novaPessoa.isAtivo());
        return repository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
