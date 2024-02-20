# 🎓 Certificação NLW

Este projeto é uma API para um aplicativo de certificações, semelhante à avaliação de competências do LinkedIn. Foi desenvolvido durante a NLW Expert trilha de Java, primeira NLW (Next Level Week) de 2024, utilizando uma variedade de tecnologias.

## 💻 Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e ferramentas:

* [JDK 17.0.6](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Initializr](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.2&packaging=jar&jvmVersion=17&groupId=com.rocketseat&artifactId=certification_nlw&name=certification_nlw&description=Projeto%20utilizado%20no%20NLW&packageName=com.rocketseat.certification_nlw&dependencies=web,devtools,lombok)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Httpie](https://httpie.io/desktop)
* [Docker](https://docs.docker.com/get-docker/)
    - [Compose plugin](https://docs.docker.com/compose/install/linux/#install-the-plugin-manually)
* [VS Code](https://code.visualstudio.com/Download)
    - [Extension Pack for Java](https://docs.docker.com/compose/install/linux/#install-using-the-repository)
    - [Spring Initializr Java Support](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-initializr)
    - [Spring Boot Tools](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-spring-boot)
    - [Spring Boot Dashboard](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-boot-dashboard)
    
## 🚀 Como Executar o Projeto

### Rodando pela primeira vez:
Inicie a base de dados com:
```bash
docker-compose up -d
```

**Alimente a base** de dados rodando o arquivo [CreateSeed.java](src\main\java\com\rocketseat\certification_nlw\seed\CreateSeed.java)

Agora pode iniciar a API com:

```bash
mvn spring-boot:run
```

### Vezes seguintes:
Para iniciar o projeto, siga os seguintes passos:

```bash
docker-compose up -d
mvn spring-boot:run
```

### Encerrar o docker
Para parar o docker, execute o seguinte comando:

```bash
docker-compose stop
```

## 📡 Requisições REST

Utilizando o Httpie para fazer requisições HTTP, é possível realizar testes e interagir com a API de forma simples e intuitiva.

### GET
Na nossa API as buscas de informações (GET) são feitas por **Route Params** e estão implementadas as seguintes requisições:
#### Requisição de questões
Para requisitar as questões e respectivas alternativas de uma tecnologia específica usamos o seguinte link:
```http://localhost:8085/questions/technology/JAVA```
onde `JAVA` pode ser trocado por outra tecnologia desejada.

#### Requisição do TOP10
Para requiistar o ranking top 10 dos maiores pontuadores em todas as certificações usamos o seguinte link:
```http://localhost:8085/ranking/top10```

###  POST
Na nossa API a inserção de informações (POST) são feitas por **Body Params** e estão implementadas as seguintes requisições:

#### Verificação de Certificação

Para verificar se um usuário possui uma certificação para uma terminada tecnologia usamos o seguinte link: `http://localhost:8085/students/verifyIfHasCertification`, para fazer uma requisição POST com o body seguindo a estrutura:

```JSON
{
  "email": "email@email.com",
  "technology": "JAVA"
}
```
Na techonologia devemos colcocar uma que está cadastrada no banco de dados, nesse caso "JAVA".

#### Enviar respostas
 
Para enviar as respostas das questões de uma certificação utilizmos o seguinte link: `http://localhost:8085/students/certification/answer`,
para fazer uma requesição POST com o body seguindo a estrutura:

```JSON
{
  "email": "email@email.com",
  "technology": "JAVA",
  "questionsAnswers": [
    {
      "questionID": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
      "alternativeID": "bafdf631-6edf-482a-bda9-7dce1efb1890"
    },
    {
      "questionID": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
      "alternativeID": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
    },
    {
      "questionID": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
      "alternativeID": "d3e51a56-9b97-4bb8-9827-8bcf89f4b276"
    }
  ]
}
```
As questões devem estar cadastradas e referenciadas pelos seus UUIDs assim com as alternativas selecionadas pelo usuário.

## 📝 Anotações:

No arquivo [Anotações.md](Anotacoes.md) eu fiz algumas anotações durante as aulas.

## 📜 Lincensa

[MIT License](https://github.com/ISS2718/Certification_NLW/blob/main/LICENSE) © [Isaac Soares](https://github.com/ISS2718)
