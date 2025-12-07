package com.pessoa.repository.adapter;

import org.springframework.data.domain.Page;

import com.pessoa.entity.Pessoa;
import com.pessoa.repository.orm.PessoaOrm;

public class PessoaRepositoryAdapter {
    public static Pessoa toEntity(PessoaOrm orm) {
        return new Pessoa(
            orm.id(),
            orm.nome(),
            orm.dtNascimento(),
            orm.ativo()
        );
    }

    public static Page<Pessoa> toEntity(Page<PessoaOrm> page) {
        return page.map(orm ->
            new Pessoa(
                orm.id(),
                orm.nome(),
                orm.dtNascimento(),
                orm.ativo()
            )
        );
    }

    public static PessoaOrm tooOrm(Pessoa pessoa) {
        return new PessoaOrm(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getDtNascimento(),
            pessoa.isAtivo()
        );
    }
}
