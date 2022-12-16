# Projeto e tecnologias

Projeto de teste de admissão feito em java 11 usando framework Quarkus, Maven Wrapper, AWS DynamoDB, AWS Lambda, AWS S3, AWS API Gateway, AWS Cloud Formation, e principalmente AWS IAM.

#### Antes de mais nada:
Instale java 11
pode ser o oracle mesmo ou do próprio GraalVM

Não precisa instalar Maven, o projeto já tem o maven wrapper.

Instale também o AWS CLI (https://docs.aws.amazon.com/pt_br/cli/latest/userguide/getting-started-install.html) 
e SAM CLI (https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html)

Siga as recomendações do seu sistema operacional, no meu caso, foi **Windows**

Vá na console da aws e crie um grupo de usuário com o nome **lambda_dynamo_s3_cloudformation_iam_apigateway**
adicione as funções

- IAMFullAccess
- AmazonS3FullAccess
- AmazonDynamoDBFullAccess
- AmazonAPIGatewayAdministrator
- AWSCloudFormationFullAccess
- AWSLambda_FullAccess

e crie um perfil chamado **dorotech** com esse grupo de permissões (**lambda_dynamo_s3_cloudformation_iam_apigateway**)
salve as chaves *AWS_KEY_ID* e *AWS_SECRET_ID*

Após criar o usuário dorotech, utilize o comando abaixo no terminal da sua máquina

```shell script
aws configure
```
No comando acima, insira as chaves AWS_KEY_ID e AWS_SECRET_ID gerados no momento da criação do usuário e use a zona que melhor convier

#### O que fazer no Dynamo
Crie uma tabela chamada **product** com o campo chave **name**
Como é apenas para teste, pode usar as configurações padrões que a aws oferecer no momento da criação
**É importante que vc crie na mesma zona.**

#### O que fazer no Amazon S3
Crie um bucket chamado **dorotech-admission-test**
**É importante que vc crie na mesma zona.**

### Um pouco mais do projeto

Criei testes unitários básicos, de GET, POST, PUT e DELETE.
Voce pode validar os testes usando os comandos:
```shell script
./mvnw clean verify
```

Iniciei o projeto sem as libs do dynamo e do lambda,
depois fiz umas pesquisas e consegui sair do outro lado

**Obs.: infelizmente essa é minha primeira vez mexendo com quarkus**

## Empacotando e fazendo deploy
Com o sam instalado vamos rodar alguns comandos agora

### Empacotando a aplicação

A aplicação pode ser empacotada usando:
```shell script
./mvnw clean package
sam package --template-file .\target\sam.jvm.yaml --output-template-file packaged.yaml --s3-bucket dorotech-admission-test
```

Esse comando vai zipar o projeto e fazer deploy dele direto no bucket **dorotech-admission-test** que criamos anteriormente.

### Fazendo deploy da aplicação

Esse comando demora um pouco mais, mas basicamente, ele vai utilizar o packaged.yaml que geramos no comando anterior, que nada mais é do que 
```shell script
sam deploy --template-file packaged.yaml --capabilities CAPABILITY_IAM --stack-name stack-dorotech-lambda-http
```
No final da execução ele vai te disponibilizar um endereço do api gateway

Dentro do projeto tem a collection.json do postman. (Dorotech Test.postman_collection.json)

## Pra finalizar

Por fim, vá até o serviço do IAM, na guia de gerenciamento de acesss, no menu de **Funções** ou **Functions** procure pela função com prefixo "stack-dorotech-lambda" e clique nela, na próxima janela, do lado direito, vá em **Adicionar Permissões -> Anexar Políticas** 
Adicione a polítia
- AmazonDynamoDBFullAccess

Com isso a lambda vai ter permissão de acesso pra ler/escrever no dynamo.

Caso vc não queira fazer isso, basta ir no packaged.yaml no caminho:
```shell script
Resources> AdmissiontestJavaQuarkusMavenDynamoLambdaRafael> Properties> Policies
e ao invés de deixar

Policies: AWSLambdaBasicExecutionRole

mude para

Policies: 
    - AWSLambdaBasicExecutionRole
    - AmazonDynamoDBFullAccess

é de extrema importancia tomar cuidado com os espaçamentos, 
precisa ficar alinhado, senão dá problema no arquivo
```

##### 1000 desculpas não ter feito o swagger, perdi um tempo considerável aprendendo o framework ##### 

É isso, qualquer coisa só me chamar :
Rafael Silva Oliveira
+55 11 982018310
