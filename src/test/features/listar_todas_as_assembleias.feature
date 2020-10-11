# language: pt

Funcionalidade: Listar Todas as Assembleias

  Cenario: 01 - Listar Todas as Assembleias
    Dado que existam as pautas cadastradas
      | Descricao                             |
      | Criação de novo fundo de investimento |
      | Emissão de novas cotas do fundo ABC   |
    E que existam as assembleias cadastradas
      | Descricao Pauta                       | Tempo Duracao |
      | Criação de novo fundo de investimento | 1             |
      | Emissão de novas cotas do fundo ABC   | 2             |
    Quando listar todas as assembleias
    Entao deveria retornar as seguintes assembleias
      | Descricao Pauta                       | Tempo Duracao |
      | Criação de novo fundo de investimento | 1             |
      | Emissão de novas cotas do fundo ABC   | 2             |