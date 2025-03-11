# language: pt

Funcionalidade: Listar Todos os Votos

  Cenario: 01 - Listar Todos os Votos
    Dado que existam as pautas cadastradas
      | Descricao                             |
      | Criação de novo fundo de investimento |
      | Emissão de novas cotas do fundo ABC   |
    E que existam os associados cadastrados
      | Nome          | cpf         |
      | Morten Harket | 44934360000 |
      | Bryan Adams   | 89622735002 |
    E que existam as assembleias cadastradas
      | Descricao Pauta                       | Tempo Duracao |
      | Criação de novo fundo de investimento | 1             |
      | Emissão de novas cotas do fundo ABC   | 2             |
    E que existam os votos cadastrados
      | Nome Associado | Descricao Pauta                       | Tipo Voto |
      | Morten Harket  | Criação de novo fundo de investimento | SIM       |
      | Bryan Adams    | Emissão de novas cotas do fundo ABC   | NAO       |
    Quando listar todos os votos
    Entao deveria retornar os seguintes votos
      | Nome Associado | Descricao Pauta                       | Tipo Voto |
      | Morten Harket  | Criação de novo fundo de investimento | SIM       |
      | Bryan Adams    | Emissão de novas cotas do fundo ABC   | NAO       |