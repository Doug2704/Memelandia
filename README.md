# Memelandia
Transformando uma aplicação monolítica em microserviços.
Projeto final do curso Especialista Back-End Java - EBAC. 
Está sob a licensa MIT - livre para qualquer finalidade. Reservado o direito de autoria.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) ![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)

Esse projeto foi desenvolvido para demonstrar como vários serviços, cada um com sua própria entidade e banco de dados,
podem se comunicar entri si via API-Gateway. Essa é uma aplicação back-end, é necessário integrá-la a uma aplicação front-end 
para o seu total funcionamento.

## Conteúdo

- [Instalação](#Instalação)
- [Uso](#Uso)
- [Banco de Dados](#banco-de-dados)
- [Contribuição](#contribuição)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/Doug2704/Memelandia.git
```

2. 

```bash
altere as variáveis de ambiente conforme a necessidade, 
ou insira manualmente nos arquivos application-dev.properties ou application-prod.properties
```
## Uso

1. Abra o projeto em sua IDE preferida ou via terminal de comando.

2. abra a pasta de cada microsserviço e execute-o dentro de sua pasta. Ex: Serviço de Usuários:

```bash
CD userservice\src\main\java\com\candido\userservice
mvn spring-boot:run
```
faça isso para todos os serviços dentro da pasta \Memelandia

3. A aplicação poderá ser iniciada a partir de um conteiner docker. 
Basta acessar a pasta \Memelandia e executar o comando:

```bash
docker-compose up
```
**obs**: Ao executar esse comando, não é necessário realizar o passo 2 pois o Docker irá iniciar todos os serviços.

4. A aplicação estará acessível em http://localhost:8080
e fará o redirecionamento para a porta do microsserviço correspondente.
**obs**: Verifique se há alguma aplicação utilizando cada referida porta localmente e altere conforme a necessidade.

## Banco de Dados
O projeto utiliza o  <span style="color:#0366d6;">PostgreSQL</span> como banco de dados.

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, abra uma issue ou envie um pull request para o repositório.

Ao contribuir para este projeto, siga o estilo de código existente, as [convenções de commit](https://www.conventionalcommits.org/en/v1.0.0/) e envie suas alterações em uma branch separada.

