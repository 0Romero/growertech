# GrowerTech API

Esta é uma API RESTful para gerenciar clientes e gerar recomendações de produtos para uma empresa chamada GrowerTech.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring HATEOAS
- SpringDoc OpenAPI (Swagger)

## Funcionalidades

- CRUD (Create, Read, Update, Delete) de clientes
- Cadastro e busca de endereços dos clientes
- Geração de recomendações de produtos para clientes

## Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/growertech-api.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd growertech-api
   ```

3. Execute o projeto usando Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a documentação da API em:

   ```
   http://localhost:8080/swagger-ui/
   ```

## Endpoints Principais

- `GET /clientes`: Retorna todos os clientes cadastrados.
- `GET /clientes/{cpf}`: Retorna um cliente específico com base no CPF.
- `POST /clientes`: Cria um novo cliente.
- `PUT /clientes/{cpf}`: Atualiza as informações de um cliente existente.
- `DELETE /clientes/{cpf}`: Remove um cliente com base no CPF.
- `POST /clientes/{cpf}/endereco`: Cadastra um novo endereço para o cliente.
- `GET /clientes/{cpf}/endereco`: Retorna o endereço associado ao cliente.
- `POST /clientes/{cpf}/recomendacao`: Gera uma recomendação de produtos para o cliente.


