package com.pessoa.repository.orm;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

@Document("pessoas")
public record PessoaOrm(
    @Id
    Long id,
    String nome,
    LocalDate dtNascimento,
    boolean ativo
) {}

