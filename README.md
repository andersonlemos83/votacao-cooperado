# Sobre o projeto votacao-cooperado

Este projeto foi concebido como parte de uma avaliação técnica de backend para tentativa de ingresso na empresa DBC Company.

**1. Domínio**

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. 

**2. Features implementadas**
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

**3. Features bônus**
- Bônus 1: Foi integrado com o sistema externo de validação de CPF;
- Bônus 3: Foi versionado em um repositório privado no Github (este!) onde foram realizados commits regulares e auto explicativos.

**4. Informações técnicas**
- Linguagem Java 8
- Ecossistema do Spring Boot 2.3.4
- Banco H2
  - Arquivo: Ambientes de desenvolvimento e produção
  - Memória: Ambientes de testes
- Testes com JUnit, Mockto e Cucumber
- Documentação da API e seus serviços com Swagger (springdoc-openapi-ui)

**5. Sobre os testes**

Com o intuito de organizar melhor os testes do projeto, foram agrupados os testes três grandes suites:
- CucumberTest: Esta suite agrupa todos os testes aceitação e integração baseados em features BDD. Ela atingiu **89%** de linha cobertas segundo coverage. 
- UnitTests: Esta suite agrupa todos os testes de unidade do projeto. Ela atingiu **86%** de linha cobertas segundo coverage.
- AllTests: Esta suite agrupa todos os testes implementados. Consiste na união do CucumberTest com UnitTests. Ela atingiu **92%** de linha cobertas segundo coverage.

OBS: Alguns testes da feature Cadastrar Associado podem apresentar intermitência devido a lentidão de resposta da API de validação de CPF.

**6. URL's**
- API: http://localhost:8081
- Swagger UI: http://localhost:8081/swagger-ui.html

**7. Lista de serviços**

Apesar da documentação da API ser gerada via Swagger, segue uma breve listagem dos serviços disponíveis:
- Listar todas as Pautas: GET - /api/pauta
- Cadastrar uma nova Pauta: POST - /api/pauta
- Buscar Pauta Consolidada por ID: GET - /api/pauta/{id}
- Listar todas as Assembleias: GET - /api/assembleia
- Cadastrar uma nova Assembleia: POST - /api/assembleia
- Listar todos os Associados: GET - /api/associado
- Cadastrar um novo Associado: POST - /api/associado
- Listar todos os Votos: GET - /api/voto
- Cadastrar um novo Voto: POST - /api/voto

**8. Sugestão de uso**

O usuário tem total liberdade para utilizar a API e seus serviços na ordem que desejar. No entanto, apenas como sugestão, segue um breve roteiro: 
1. Criar uma nova pauta; 
2. Criar uma nova assembĺeia relacionando a pauta do item 1; 
3. Criar um novo associado; 
4. Criar um novo voto relacionando com a assembleia criada no item 2 e com o associado criado no item 3; 
5. Consultar o consolidado da pauta criada no item 1.

**9. Trabalhos futuros(!?)**

Gostaria de utilizar este espaço para debater um pouco mais sobre as features que ficaram de fora desta implementação: 
- Bônus 2 (Mensageria e filas): Tive um breve vislumbre de integração entre Abache Kafka e a API votacao-cooperado. No entanto, devido ao tempo disponível e ao meu modesto conhecimento sobre o tema, não consegui avançar com a solução; 
- Bônus 3 (Performance): 
  - Como melhorar: Para melhorar a performance a API, eu optaria por executá-la dentro de um container Docker escalado em N hosts em um orquestrador de containers, com Load Balance e Health Check; 
  - Como testar: Para testar a performance da API, eu optaria por criar um plano de teste simulando N requisições, utilizando o Apache JMeter, e analisaria os resultado através de um ouvinte. 
- Hospedagem: Assim como a API de validação de CPF, poderia ter apresentado a API votacao-cooperado utilizando o próprio Heroku.
