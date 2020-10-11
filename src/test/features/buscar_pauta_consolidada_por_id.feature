# language: pt

Funcionalidade: Buscar Pauta Consolidada Por ID

  Cenario de Fundo:
    Dado que existam as pautas cadastradas
      | id | Descricao                               |
      | 1  | Emissão de novas cotas do fundo Musical |
    E que existam os associados cadastrados
      | Nome              | cpf         |
      | Morten Harket     | 44934360000 |
      | Bryan Adams       | 89622735002 |
      | Agnetha Fältskog  | 01387268090 |
      | Dolores O'Riordan | 64951402076 |
      | Simon Le Bon      | 89622735002 |
      | Jon Bon Jovi      | 37984192057 |
      | Russell Hitchcock | 61022326074 |
      | Cyndi Lauper      | 12203726091 |
      | Laura Pausini     | 08699056001 |
      | Klaus Meine       | 78186436057 |
    E que existam as assembleias cadastradas
      | Descricao Pauta                         | Tempo Duracao |
      | Emissão de novas cotas do fundo Musical | 1             |
    E que existam os votos cadastrados
      | Nome Associado    | Descricao Pauta                         | Tipo Voto |
      | Morten Harket     | Emissão de novas cotas do fundo Musical | SIM       |
      | Bryan Adams       | Emissão de novas cotas do fundo Musical | SIM       |
      | Agnetha Fältskog  | Emissão de novas cotas do fundo Musical | SIM       |
      | Dolores O'Riordan | Emissão de novas cotas do fundo Musical | SIM       |
      | Simon Le Bon      | Emissão de novas cotas do fundo Musical | SIM       |
      | Jon Bon Jovi      | Emissão de novas cotas do fundo Musical | SIM       |
      | Russell Hitchcock | Emissão de novas cotas do fundo Musical | NAO       |
      | Cyndi Lauper      | Emissão de novas cotas do fundo Musical | NAO       |
      | Laura Pausini     | Emissão de novas cotas do fundo Musical | NAO       |
      | Klaus Meine       | Emissão de novas cotas do fundo Musical | NAO       |

  Cenario: 01 - Buscar Pauta Consolidada Por ID existente
    Quando buscar pauta consolidada por ID "1"
    Entao deveria retornar a seguinte pauta consolidada
      | Descricao Pauta                         | Status Assembleia | Quantidade Votos Sim | Quantidade Votos Nao |
      | Emissão de novas cotas do fundo Musical | ABERTA            | 6                    | 4                    |

  Cenario: 02 - Buscar Pauta Consolidada Por ID nao existente
    Quando buscar pauta consolidada por ID "10"
    Entao deveria retornar a mensagem "A pauta informada não exite"