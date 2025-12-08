package com.pessoa.configuration.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pessoa.entity.Pessoa;
import com.pessoa.repository.PessoaRepositoryPostgres;
import com.pessoa.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepositoryPostgres repo;

    private static final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

    public PessoaServiceImpl(PessoaRepositoryPostgres repo) {
        this.repo = repo;
    }

    @Override
    public Pessoa criar(Pessoa pessoa) {
        Pessoa pessoa_criada = repo.save(pessoa);
        log.info("Pessoa criada com sucesso! ID: {}", pessoa_criada.getId());
        return pessoa_criada;
    }

    @Override
    public Page<Pessoa> listar(Pageable pageable) {
        log.info("Pessoas listadas com sucesso!");
        return repo.findAll(pageable);
    }

    @Override
    public Pessoa pesquisar(Long id) {
        log.info("Buscando pessoa com, ID: {}", id);

        return repo.findById(id)
            .orElseThrow(() ->  new RuntimeException("Pessoa não encontrada, - ID {"+ id +"}"));
    }

    @Override
    public Pessoa atualizar(Long id, Pessoa novaPessoa) {
        log.info("Atualizar pessoa com, ID {}", id);

        Pessoa existente = pesquisar(id);
        existente.setNome(novaPessoa.getNome());
        existente.setDtNascimento(novaPessoa.getDtNascimento());
        existente.setAtivo(novaPessoa.isAtivo());
    
        Pessoa pessoa_atualizada = repo.save(existente);
        log.info("Pessoa atualizada com sucesso, ID {}", pessoa_atualizada.getId());
        return pessoa_atualizada;
    }

    @Override
    public void deletar(Long id) {
        log.info("Excluindo pessoa com ID: {}", id);
        repo.deleteById(id);
        log.info("Pessoa excluída com sucesso! ID: {}", id);
    }

    @Override
    public Page<Pessoa> listarTudo(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Pessoa> resultado = repo.findAll(pageable);

        log.info(
            "Listagem completa realizada | Página: {} | Registros nesta página: {} | Total de páginas: {} | Total de registros: {}",
             page,
            resultado.getNumberOfElements(),
            resultado.getTotalPages(),
            resultado.getTotalElements()
        );

        return resultado;
    }
}
