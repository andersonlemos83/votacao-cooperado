[![Build Status](https://travis-ci.org/andersonlemos83/votacao-cooperado.svg?branch=main)](https://travis-ci.org/andersonlemos83/votacao-cooperado) [![codecov](https://codecov.io/gh/andersonlemos83/votacao-cooperado/branch/main/graph/badge.svg?token=BYOJWGTKMR)](https://codecov.io/gh/andersonlemos83/votacao-cooperado)

# Sobre o projeto Votação Cooperado API

Este projeto foi concebido como parte de uma avaliação técnica de backend para tentativa de ingresso na empresa DBC Company.

**Notas da versão 0.0.2-SNAPSHOT**
- Substitui os conversores por mappers;
- Adiciona builders para o ambiente de testes;
- Substitui várias iterações com "for" por "forEach";
- Adiciona versionamento por URI para os endpoints da API;
- Implementa novos casos de testes para features Cucumber;
- Implementa novos testes de unidade;
- Configura o plugin Maven surefire para execução de testes no Travis CI;
- Remove trechos de código morto e realiza vários pequenos refatoramentos. 

**1. Domínio**

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias por votação. 

**2. Features implementadas**
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

**3. Features bônus implementadas**
- Bônus 1: Foi integrado com o sistema externo de validação de CPF;
- Bônus 4: Foi versionada a API utilizando a estratégia por URL, mais especificamente, por path ou URI. Assim, foram acrescentados a todos os endpoints da API o préfixo de URI "/v1" que define o seu primeiro contrato. Foi optado por esta estratégia de versionamento por julgar ser a mais difundida e a mais simples de ser adotada. No entanto, não descartamos a adoção futura de uma abordagem mista com a estratégia de Headers customizados, onde a estratégia por URI marcaria as grandes alterações de contrato da API e a estratégia de Headers marcaria pequenos ajustes no contrato da API.  

**4. Informações técnicas**
- Linguagem Java 8
- Ecossistema do Spring Boot 2.3.4
- Banco H2
  - Arquivo: Ambientes de desenvolvimento e produção
  - Memória: Ambientes de testes
- Testes com JUnit, Mockto e Cucumber
- Conversão de entidades em DTO e vice-versa com Model Mapper
- Documentação da API e seus serviços com Swagger (springdoc-openapi-ui)
- Integração Contínua com Travis CI

**5. Sobre os testes**

Com o intuito de organizar melhor os testes do projeto, foram agrupados os testes em três grandes suites:
- CucumberTest: Esta suite agrupa todos os testes aceitação e integração baseados em features BDD. Ela atingiu **85%** de linha cobertas segundo coverage. 
- UnitTests: Esta suite agrupa todos os testes de unidade do projeto. Ela atingiu **81%** de linha cobertas segundo coverage.
- AllTests: Esta suite agrupa todos os testes implementados. Consiste na união do CucumberTest com UnitTests. Ela atingiu **88%** de linha cobertas segundo coverage.

OBS: Alguns testes da feature Cadastrar Associado podem apresentar intermitência devido a lentidão de resposta da API de validação de CPF.

**6. URL's**
- API: http://localhost:8081
- Swagger UI: http://localhost:8081/swagger-ui.html

**7. Lista de serviços**

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

**8. Sugestão de uso**

O usuário tem total liberdade para utilizar a API e seus serviços na ordem que desejar. No entanto, apenas como sugestão, segue um breve roteiro: 
1. Criar uma nova pauta; 
2. Criar uma nova assembĺeia relacionando a pauta do item 1; 
3. Criar um novo associado; 
4. Criar um novo voto relacionando com a assembleia criada no item 2 e com o associado criado no item 3; 
5. Consultar o consolidado da pauta criada no item 1.

**9. Trabalhos futuros**

Gostaria de utilizar este espaço para debater um pouco mais sobre as features que ficaram de fora desta implementação: 
- Bônus 2 (Mensageria e filas): Tive um breve vislumbre de integração entre Apache Kafka e o projeto Votação Cooperado API. No entanto, devido ao tempo disponível e ao meu modesto conhecimento sobre o tema, não consegui avançar com a solução; 
- Bônus 3 (Performance): 
  - Como melhorar: Para melhorar a performance da API, eu optaria por executá-la dentro de um container Docker escalado em vários hosts em um orquestrador de containers, com Load Balance, Health Check e Pool de conexões; 
  - Como testar: Para testar a performance da API, eu optaria por criar um plano de teste simulando várias requisições, utilizando o Apache JMeter, e analisaria os resultado através de um ouvinte. 
- Hospedagem: Assim como a API de validação de CPF, poderia ter apresentado o projeto Votação Cooperado API utilizando o próprio Heroku.
- Nome do projeto: O nome do projeto e seu repositório acabou ficando votacao-cooperado, quando o correto deveria ser votacao-cooperado-api. Preferi não fazer nenhuma alteração para não atrapalhar a entrega.

**10. That's all folks!**

Caro avaliador, se você chegou até este ponto, eu espero ter causado uma boa impressão.
