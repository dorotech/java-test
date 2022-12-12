# Desafio Back End Java na DoroTech

### Projeto
    Desenvolvimento de uma API REST para o gerenciamento de produtos eletrônicos com Spring Webflux e MongoDB.
### Tecnologias
        - Java 19
        - Spring 3.0.0
        - Lombok
        - Gradle para gerenciamento de dependências
        - MapStruct para a conversão entre classes do domínio e DTOs
        - Testes automatizados com Mockito e WebTestClient
        - Banco de dados NoSQL MongoDB Atlas
        - Swagger e OpenAPI para documentação da API.
        - Docker e Amazon ECS para o deploy da aplicação.
### Como executar o projeto:

Para acessar o projeto é possível seguir as seguintes alternativas:
1. Realizar o download da [imagem docker](https://hub.docker.com/repository/docker/ferreirabrunomarcelo/product-service) e executar os seguintes comandos:

a. `docker pull ferreirabrunomarcelo/product-service:latest`

b. `docker run --publish 8081:8081 ferreirabrunomarcelo/product-service`

c. acessar pelo navegador a URI `http://localhost:8081/swagger-ui.html`

2. Acessar a API por meio do IP público do [Amazon ECS](http://177.71.160.52:8081/swagger-ui.html).