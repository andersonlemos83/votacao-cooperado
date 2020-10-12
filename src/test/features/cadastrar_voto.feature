# language: pt

Funcionalidade: Cadastrar Voto

  Cenario de Fundo:
    Dado que existam as pautas cadastradas
      | id | Descricao                               |
      | 1  | Emissão de novas cotas do fundo Musical |
      | 2  | Criação de novo fundo de investimento   |
    E que existam os associados cadastrados
      | id | Nome             | cpf         |
      | 1  | Jon Bon Jovi     | 37984192057 |
      | 2  | Agnetha Fältskog | 01387268090 |
    E que existam as assembleias cadastradas
      | id | Descricao Pauta                         | Tempo Duracao | Data Criacao               |
      | 1  | Emissão de novas cotas do fundo Musical | 2             | DATA_ATUAL                 |
      | 2  | Criação de novo fundo de investimento   | 2             | DATA_EXPIRADA_TRES_MINUTOS |
    E que existam os votos cadastrados
      | Nome Associado   | Descricao Pauta                         | Tipo Voto |
      | Agnetha Fältskog | Emissão de novas cotas do fundo Musical | NAO       |

  Cenario: 01 - Cadastrar Voto Com Sucesso
    Dado que seja informado o associado "1"
    E que seja informado a assembleia "1"
    E que seja informado o voto "SIM"
    Quando cadastrar voto
    Entao deveria cadastrar o seguinte voto
      | Nome Associado | Descricao Pauta                         | Tipo Voto |
      | Jon Bon Jovi   | Emissão de novas cotas do fundo Musical | SIM       |

  Cenario: 02 - Cadastrar Voto Sem Informar Decisao
    Dado que seja informado o associado "1"
    E que seja informado a assembleia "1"
    E que seja informado o voto ""
    Quando cadastrar voto
    Entao deveria retornar a mensagem "O tipo de voto é obrigatório"

  Cenario: 03 - Cadastrar Voto Sem Informar Associado
    Dado que seja informado o associado ""
    E que seja informado a assembleia "1"
    E que seja informado o voto "SIM"
    Quando cadastrar voto
    Entao deveria retornar a mensagem "O id do associado é obrigatório"

  Cenario: 04 - Cadastrar Voto Sem Informar Associado
    Dado que seja informado o associado "1"
    E que seja informado a assembleia ""
    E que seja informado o voto "SIM"
    Quando cadastrar voto
    Entao deveria retornar a mensagem "O id da assembleia de votação é obrigatório"

  Cenario: 05 - Cadastrar Voto Informando Assembleia Inexistente
    Dado que seja informado o associado "1"
    E que seja informado a assembleia "11"
    E que seja informado o voto "SIM"
    Quando cadastrar voto
    Entao deveria retornar a mensagem "A assembleia informada não existe"

  Cenario: 06 - Cadastrar Voto Informando Assembleia Fechada
    Dado que seja informado o associado "1"
    E que seja informado a assembleia "2"
    E que seja informado o voto "SIM"
    Quando cadastrar voto
    Entao deveria retornar a mensagem "A assembleia informada está fechada"

  Cenario: 07 - Cadastrar Voto Pela Segunda Vez na Assembleia
    Dado que seja informado o associado "2"
    E que seja informado a assembleia "1"
    E que seja informado o voto "SIM"
    Quando cadastrar voto
    Entao deveria retornar a mensagem "O associado já exerceu seu direito de voto para esta pauta"