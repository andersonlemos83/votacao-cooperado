# language: pt

Funcionalidade: Listar Todas as Pautas

    Cenario: 01 - Listar Todas as Pautas
        Dado que existam as pautas cadastradas
            | Descricao                             |
            | Criação de novo fundo de investimento |
            | Emissão de novas cotas do fundo ABC   |
        Quando listar todas as pautas
        Entao deveria retornar as seguintes pautas
            | Descricao                             |
            | Criação de novo fundo de investimento |
            | Emissão de novas cotas do fundo ABC   |