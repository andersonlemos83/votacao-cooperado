# language: pt

Funcionalidade: Listar Todos os Associados

  Cenario: 01 - Listar Todos os Associados
    Dado que existam os associados cadastrados
      | Nome          | cpf         |
      | Morten Harket | 44934360000 |
      | Bryan Adams   | 89622735002 |
    Quando listar todos os associados
    Entao deveria retornar os seguintes associados
      | Nome          | cpf         |
      | Morten Harket | 44934360000 |
      | Bryan Adams   | 89622735002 |