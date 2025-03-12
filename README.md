[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_votacao-cooperado&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_votacao-cooperado)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_votacao-cooperado&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_votacao-cooperado)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_votacao-cooperado&metric=coverage)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_votacao-cooperado)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_votacao-cooperado&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_votacao-cooperado)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_votacao-cooperado&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_votacao-cooperado)

# Sobre o projeto Votação Cooperado API

Este projeto foi concebido no final do ano de 2020 como parte de uma avaliação técnica de backend para tentativa de
ingresso na empresa DBC Company.

OBS: Atualizado em 2025

**1. Domínio**

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias por votação.

**2. Features implementadas**

- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de
  abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único
  e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

**3. Features bônus implementadas**

- Bônus 1: Foi integrado com o sistema externo de validação de CPF;
- Bônus 4: Foi versionada a API utilizando a estratégia por URL, mais especificamente, por path ou URI. Assim, foram
  acrescentados a todos os endpoints da API o préfixo de URI "/v1" que define o seu primeiro contrato. Foi optado por
  esta estratégia de versionamento por julgar ser a mais difundida e a mais simples de ser adotada. No entanto, não
  descartamos a adoção futura de uma abordagem mista com a estratégia de Headers customizados, onde a estratégia por URI
  marcaria as grandes alterações de contrato da API e a estratégia de Headers marcaria pequenos ajustes no contrato da
  API.

**4. Informações técnicas**

- Linguagem Java 21 LTS
- Ecossistema do Spring Boot 3.4.3 (Web, Undertow, Log4j2, Validation, Data JPA, Actuator, Test, entre outros)
- Testes com JUnit 5, Mockito, Cucumber, WireMock e Instancio
- Banco de dados com H2:
    - Arquivo: Ambientes de desenvolvimento e produção
    - Memória: Ambientes de testes
- Documentação da API com Swagger/OpenAPI
- Comunicação entre Serviços com RestTemplate
- Containerização com Docker
- Logging e Monitoramento com Log4j2 e Spring Boot Actuator
- Controle de código Boilerplate com ModelMapper e Lombok
- Gerenciamento de dependências com o Maven
- Controle de Versão com Git
- Integração Contínua com GitHub Actions
- Análise de cobertura de testes com SonarQube

**5. Sobre os testes**

Com o intuito de organizar melhor os testes do projeto, foram agrupados os testes em três grandes suites:

- CucumberTest: Esta suite agrupa todos os testes aceitação e integração baseados em features BDD.
- UnitTests: Esta suite agrupa todos os testes de unidade do projeto.
- AllTests: Esta suite agrupa todos os testes implementados. Consiste na união do CucumberTest com UnitTests.

**6. URL's**

- API: http://localhost:8484 (profile local)
- Swagger UI: http://localhost:8484/swagger-ui.html (profile local)

**7. Wiremock para Serviços Externos**:
1. Subir uma instância do Wiremock:
  ```
    docker-compose -f .\script\docker\wiremock.yml up -d
  ```

2. Testar a instância do Wiremock: [Testar Wiremock](http://localhost:8443/users/05551874460)
  ```
    curl 'http://localhost:8443/users/05551874460'
  ```

**8. Lista de serviços**

Apesar da documentação da API ser gerada via Swagger, segue uma breve listagem dos serviços disponíveis:

- Listar todas as Pautas: GET - /v1/api/pautas
- Cadastrar uma nova Pauta: POST - /v1/api/pautas
- Buscar Pauta Consolidada por ID: GET - /v1/api/pautas/{id}
- Listar todas as Assembleias: GET - /v1/api/assembleias
- Cadastrar uma nova Assembleia: POST - /v1/api/assembleias
- Listar todos os Associados: GET - /v1/api/associados
- Cadastrar um novo Associado: POST - /v1/api/associados
- Listar todos os Votos: GET - /v1/api/votos
- Cadastrar um novo Voto: POST - /v1/api/votos

[votacao-cooperado.postman_collection.json](./script/postman/votacao-cooperado.postman_collection.json)

**9. Sugestão de uso**

O usuário tem total liberdade para utilizar a API e seus serviços na ordem que desejar. No entanto, apenas como
sugestão, segue um breve roteiro:

1. Criar uma nova pauta;
2. Criar uma nova assembĺeia relacionando a pauta do item 1;
3. Criar um novo associado;
4. Criar um novo voto relacionando com a assembleia criada no item 2 e com o associado criado no item 3;
5. Consultar o consolidado da pauta criada no item 1.

**10. That's all folks!**

Caro avaliador, obrigado pela oportunidade.
