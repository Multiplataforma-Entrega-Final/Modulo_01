## Modulo 01 - API de Pessoas
Este projeto consiste em uma API REST desenvolvida com Spring Boot, contendo um CRUD completo de Pessoa, integrado com PostgreSQL, envio de logs para o Graylog e análise estática de código com SonarQube.
***
## Funcionalidades
### A API permite:
- Criar pessoas
- Listar pessoas com paginação
- Atualizar dados de uma pessoa
- Pesquisar por ID
- Deletar uma pessoa
- Retornar somente pessoas com ativo = true
***
### Rotas
***
- POST /pessoas/path

entrada
```json
{
    "nome": "Daniel Rufino",
    "dtNascimento": "2005-06-03"
	"ativo": true
}
```
saída
```json
{
    "id": 1,
    "nome": "Daniel Rufino",
    "dtNascimento": "2005-06-03",
    "ativo": true
}
```
***
- PUT /pessoas/{id}

entrada
```json
{
    "nome": "Daniel Rufino",
    "dtNascimento": "2000-01-25",
	"ativo": false
}
```

saída
```json
{
    "id": 1,
    "nome": "Daniel Rufino",
    "dtNascimento": "2000-01-25",
    "ativo": false
}
```
***
- DELETE /pessoas/{id}
`Sem request / sem response`
`Status esperado: 204 No Content`
***
- GET /pessoas/{id}

saída
```json
{
    "id": 1,
    "nome": "Daniel Rufino",
    "dtNascimento": "2005-06-03",
    "ativo": false
}
```
***
- GET /pessoas?page=0

saída
```json
{
	"content": [
		{
			"id": 1,
			"nome": "Daniel Rufino",
			"dtNascimento": "2005-06-03",
			"ativo": false
		},
		{
			"id": 2,
			"nome": "João de Souza",
			"dtNascimento": "1995-05-10",
			"ativo": true
		}
	],
	"pageable": {
		"pageNumber": 0,
		"pageSize": 10,
		"sort": {
			"sorted": false,
			"empty": true,
			"unsorted": true
		},
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalPages": 1,
	"totalElements": 2,
	"size": 10,
	"number": 0,
	"sort": {
		"sorted": false,
		"empty": true,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 2,
	"empty": false
}
```
***
### Entidade Pessoa
```java
private Long id;
private String nome;
private LocalDate dtNascimento;
private boolean ativo;
```
***
### O projeto utilizou docker-compose com:
- Spring Boot;
- PostgreSQL;
- Graylog;
- MongoDB (para o Graylog);
- SonarQube;