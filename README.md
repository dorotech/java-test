# Desafio Back End Java na DoroTech 

Somos uma empresa com clientes que atuam em vários segmentos do mercado, com diferentes tecnologias, culturas e desafios.

Gostamos de compor nossos times com profissionais multidisciplinares, que tenham alta capacidade de aprendizado, sejam detalhistas, resilientes, questionadores e curiosos. Você, como Java Developer, será o responsável por implementar, dar manutenção, aplicar correções e propor soluções em projetos de software.


### Documentação
```
1. Desenvolvido aplicação CRUD para cadastro, recuperação, update e delete.

2. Foi utilizado o Framework SpringBoot para desenvolvimento.

3. Os dados estão sendo persistidos no Banco de dados NOSQl DynamoDB, tambem estou utilizando Docker, ECR, e ECS fargate para deploy da aplicação.

4. Utilizei processamento assincrono para criação de um novo produto, utilizando o SQS da AWS

5. As variaveis de ambiente com as credenciais da AWS serão enviadas ao avaliador
 Documentação
 
6. A aplicação foi configurada para rodar na porta 8081, assim como a porta do container que esta sendo exposta é a porta 81

7. É possivil testar e verificar parte da documentação gerada pelo swagger no link gerado pelo mesmo: http://localhost:8081/swagger-ui.html#/

8. Como rodar manualmente: 
    - va ao diretorio raiz do projeto e acesse a pasta target
    - em seguida abra no terminal a localização e entre com o seguinte comando
    - mvn spring-boot:run 
    Obs: atente-se as variaveis de ambiente

9. Caso deseje rodar via IDE recomendo fortemente o uso do Intellij, porem caso use outra IDE não deve encontrar grandes problemas pois todo o gerenciamento
de dependencias esta sendo feito pelo maven, basta apenas ter atenção ao detalhe do anotation processor do lombok e tambem adicionar as variaveis de ambiente

```

### Métodos HTTP
```
--->>>Local
    - Busca todos os produtos - GET : http://localhost:8081/products/getAllProducts
    - Cria produto - POST :  http://localhost:8081/products/createProduct
    - Encontra produto por ID - GET : GET http://localhost:8081/products/getProductById/{{id}}
    - Deleta produto por ID - DELETE : http://localhost:8081/products/deleteProduct/{{id}}
    - Atualiza produto por ID - UPDATE : http://localhost:8081/products/updateProduct/{{id}}   
   
```

### Funcionamento do programa
```

    OBS: Recomendo a utilizção do Swagger para fins de testes manuais.

    - Ao iniciar o programa localmente o mesmo ira rodar na porta local 8081 e necessitará das variaveis de ambiente para se conectar a AWS
     e por fim se conectar ao DynamoDB e ao sistema de filas SQS
    - Ja existe uma carga de dados no Banco então se ao iniciar o software e acessar o metodo GET /getAllProducts esse por sua vez deverá retornar os produtos do banco
    - Para criar um produto isso deverá ser feito através do metodo POST /createProduct, a criação de produtos ocorrerá em background com o DTO do produto sendo enviado 
    para uma fila SQS, e só após a fila ser consumida esse novo produto será persistido no banco.
    - Para fazer um update, primeiro deve-se saber o ID do produto em seguida atualizar as informação e então enviar a requisição.
    - Para se fazer um delete também é necessário saber o ID do produto que se deseja excluir.
