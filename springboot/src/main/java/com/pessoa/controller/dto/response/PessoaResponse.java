package com.pessoa.controller.dto.response;

import java.time.LocalDate;

public record PessoaResponse (
    Long id,
    String name,
    LocalDate dtNascimento,
    boolean ativo
) {
}
