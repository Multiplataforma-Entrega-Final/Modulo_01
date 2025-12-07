package com.pessoa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pessoa.entity.Pessoa;

public interface PessoaService {
    Pessoa criar(Pessoa p);
    Pessoa atualizar(Long id, Pessoa p);
    Pessoa pesquisar(Long id);
    Page<Pessoa> listar(Pageable pageable);
    void deletar(Long id);
    Page<Pessoa> listarTudo(int page);
}

