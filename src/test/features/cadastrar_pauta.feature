# language: pt

Funcionalidade: Cadastrar Pauta

  Cenario: 01 - Cadastrar Pauta Com Sucesso
    Dado que seja informado a descricao "Emissão de novas cotas do fundo Musical"
    Quando cadastrar pauta
    Entao deveria cadastrar a seguinte pauta "Emissão de novas cotas do fundo Musical"

  Cenario: 02 - Cadastrar Pauta Sem Informar Descricao
    Dado que seja informado a descricao ""
    Quando cadastrar pauta
    Entao deveria retornar a mensagem "A descrição da pauta é obrigatória"