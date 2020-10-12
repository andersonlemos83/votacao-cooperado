# Sobre o projeto votacao-cooperado

Este projeto foi concebido como parte de uma avaliação técnica de backend para tentativa de ingresso na empresa DBC Company.

**1. Domínio**
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. 

**2. Features Implementadas**
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

**3. Features Bonus**
- Bônus 1 - Foi integrado com o sistema externo de validação de CPF;
- Bônus 3 - Foi versionado em um repositório privado no Github (este!) onde foram realizados commits regulares e auto explicativos.

**4. Informações Técnicas**
- Liguagem Java 8
- Ecossitema do Spring Boot 2.3.4
- Banco H2
  - Arquivo: Ambientes de desenvolvimento e produção
  - Memória: Ambientes de testes

