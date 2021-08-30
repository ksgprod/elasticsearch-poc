# Visão geral

O projeto é um serviço back-end, com o objetivo de abordar de forma simples a utilização da API do Elasticsearch.

Para isso, foi utilizada uma imagem do Elasticsearch rodada via container Docker. Foram criadas algumas entidades com a finalidade de exemplificar consultas básicas em cima de uma massa de dados disponibilizada através do serviço "init" para ambas.

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone, com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.

- [Spring Data](https://spring.io/projects/spring-data) fornece um modelo familiar e consistente de programação, baseado em Spring, para acesso a dados, mantendo as características especiais do armazenamento de dados subjacente.
Facilita o uso de tecnologias de acesso a dados, bancos de dados relacionais e não relacionais, estruturas de redução de mapa e serviços de dados baseados em nuvem.

 
# Setup da aplicação (local)

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 11
Maven
Docker
```

## Instalação da aplicação

Primeiramente, faça o clone do repositório:
```
git clone https://github.com/ksgprod/elasticsearch-poc.git
```
Feito isso, acesse o projeto:
```
cd elasticsearch-simple-poc
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean install
```
É preciso startar o container Docker para o Elasticsearch (o mesmo rodará na porta padrão: 9200):
```
docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http) with context path ''
Started ElasticsearchSimplePocApplication in XXXX seconds (JVM running for XXX)
```
Uma collection Postman está disponibilizada na subpasta "collections" da aplicação.
```
./collections/POC - Elactic Search.postman_collection.json
```