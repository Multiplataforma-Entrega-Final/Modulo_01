package com.pessoa.configuration.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pessoa.repository.PessoaRepository;
import com.pessoa.service.PessoaService;
import com.pessoa.service.PessoaServiceImpl;

@Configuration
public class PessoaConfigService {

    @Bean
    public PessoaService pessoaService(PessoaRepository repository) {
        return new PessoaServiceImpl(repository);
    }
}
