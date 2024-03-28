# 💻 API Book - Cadastro de Livros
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

---------
## SOBRE
A API Book foi desenvolvida com intuito de praticar programação com Sprint Boot, também com MySQL que posteriormente foi trocado para PostgreSQL.
Nessa API podemos estar cadastrando livros atráves do Postman e fazendo requisições HTTP (Get, Post, Put e Delete). Pode estar fazendo o Git clone e fazendo o clone do repositório para que possa estar executando a aplicação.

---------
## Comandos Git
### Clonar Repositório

      git clone git@github.com:omattaeus/apibook.git

O repositório também estará disponível para estudantes e desenvolvedores no geral que queiram fazer contribuição na API Book!

### Fork, Commit e Pull Request
No repositório aqui no GitHub tem a opção de "Fork", logo no menu acima no canto direito. Após ter feito o Fork e o Git Clone, terá como fazer os commits e pull request para o projeto.
#### Git commit (comandos)
    git init
    git add .
    git remote add origin "url do repositório do qual foi feito o fork"
    git push origin "sua-branch"

Logo após o commit, podera estar fazendo o pull request para o projeto :)

## URL para fazer Autenticação
### Método POST

    localhost:8080/auth/signin

Logo após a autenticação, podemos estar pegando o "Acess Token" e colocando como "Authorization", ou seja, "Bearer (access token-gerado)";
## URL para fazer as requisições HTTP
Nessa URL abaixo, podemos estar fazendo O GET de todos os dados que estão salvo no banco de dados, e ele retornará todos os livros salvos.
    
    localhosT:8080/api/book/v1

### JSON do GET Todos os Livros
    {
      "_embedded": {
          "bookVOes": [
              {
                  "id": 16,
                  "author": "Aguinaldo Aragon Fernandes e Vladimir Ferraz de Abreu",
                  "launchDate": "2017-11-07T02:00:00.000+00:00",
                  "price": 54.0,
                  "title": "Implantando a governança de TI",
                  "enabled": true,
                  "_links": {
                      "self": {
                          "href": "http://localhost:8080/api/book/v1/16"
                      }
                  }
    }

### JSON do GET Apenas Um Livro
No JSON abaixo estamos utilizando a seguinte URL para requisição de GET um elemento, ou seja, retornará apenas um livro que está salvo no banco de dados.

      localhost:8080/api/book/v1/:id
      
### JSON

    {
        "id": 10,
        "author": "Brian Goetz e Tim Peierls",
        "launchDate": "2017-11-07T02:00:00.000+00:00",
        "price": 80.0,
        "title": "Java Concurrency in Practice",
        "enabled": true,
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/book/v1/10"
            }
        }
    }

### JSON do Post para Cadastrar um Novo Livro
Na URL abaixo podemos atráves dela estar fazendo a requisição POST para cadastrar um novo elemento, ou seja, um novo livro.

    localhosT:8080/api/book/v1

### JSON
    {
        "author": "Some Name",
        "launchDate": "2024-03-14T01:44:23.464Z",
        "price": 17.85,
        "title": "Clean Code",
        "enabled": true
    }

### JSON do PUT para Alterar um Dado Especifíco do Livro
Na URL abaixo podemos estar alterando um dado especifíco do livro, por exemplo, podemos estar alterando apenas o preço do livro, sem afetar os outros campos.

    localhost:8080/api/book/v1

### JSON

    {
      "id": "<long>",
      "author": "<string>",
      "launchDate": "<dateTime>",
      "price": "<double>",
      "title": "<string>",
    }

### URL do Delete para estar apagando um livro do banco de dados.
Na URL abaixo podemos estar deletando um livro do banco de dados.

    localhost:8080/api/book/v1/:id

Se a exclusão do dado do livro estiver tudo ok, ele devera retornar "204 no content", ou seja, ocorreu tudo bem e o dado do livro foi apagado com sucesso!

--------
## Vamos nos conectar :handshake:

<a href="https://www.linkedin.com/in/mateusgd/"><img src="https://cdn2.iconfinder.com/data/icons/social-media-2285/512/1_Linkedin_unofficial_colored_svg-128.png" width="40"></a>|
|--
