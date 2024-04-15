# GrowerTech API

Esta é uma API para fornecer recomendações de cultivo com base nas condições do solo, clima e outras variáveis dos clientes.

## Endpoints

### Clientes

#### Listar todos os clientes
- **Método HTTP**: GET
- **URL**: /clientes
- **Descrição**: Retorna uma lista de todos os clientes cadastrados.

#### Buscar cliente por CPF
- **Método HTTP**: GET
- **URL**: /clientes/{cpf}
- **Descrição**: Retorna um cliente com base no CPF fornecido.

#### Criar cliente
- **Método HTTP**: POST
- **URL**: /clientes
- **Descrição**: Cria um novo cliente com os dados fornecidos.

#### Atualizar cliente
- **Método HTTP**: PUT
- **URL**: /clientes/{id}
- **Descrição**: Atualiza os dados de um cliente existente com base no ID fornecido.

#### Deletar cliente
- **Método HTTP**: DELETE
- **URL**: /clientes/{id}
- **Descrição**: Remove um cliente com base no ID fornecido.

### Recomendações

#### Gerar recomendação para um cliente
- **Método HTTP**: GET
- **URL**: /clientes/{cpf}/recomendacao
- **Descrição**: Gera uma recomendação de cultivo com base nas informações do cliente.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Hibernate
- H2 Database (em memória)
- Lombok
- Bean Validation

## Executando a aplicação

Para executar a aplicação localmente, você pode seguir os seguintes passos:

1. Certifique-se de ter o JDK 8 (ou superior) instalado em sua máquina.
2. Clone este repositório em sua máquina local.
3. Navegue até o diretório da aplicação.
4. Execute o comando `./mvnw spring-boot:run` para iniciar a aplicação.
5. Acesse os endpoints da API usando um cliente HTTP como o Postman ou o Insomnia.

