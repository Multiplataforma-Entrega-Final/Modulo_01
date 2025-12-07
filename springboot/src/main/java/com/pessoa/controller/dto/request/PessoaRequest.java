package com.pessoa.controller.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotNull;

public record PessoaRequest(
    Long id,
    @NotBlank(message = "Nome")
    String nome,
    @NotNull(message = "Data de nascimento")
    @Past(message = "Data de nascimento não pode ser marcada em data inexistente")
    LocalDate dtNascimento,
    boolean ativo
) {
}