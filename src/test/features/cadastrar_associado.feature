# language: pt

Funcionalidade: Cadastrar Associado

  Cenario de Fundo:
    Dado que existam os associados cadastrados
      | Nome        | cpf         |
      | Klaus Meine | 78186436057 |

  Cenario: 01 - Cadastrar Associado Com Sucesso
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF "08699056001"
    Quando cadastrar associado
    Entao deveria cadastrar o seguinte associado
      | Nome          | cpf         |
      | Laura Pausini | 08699056001 |

  Cenario: 02 - Cadastrar Associado Sem Informar Nome
    Dado que seja informado o nome ""
    E que seja informado o CPF "08699056001"
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O nome do associado é obrigatório"

  Cenario: 03 - Cadastrar Associado Sem Informar CPF
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF ""
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O CPF do associado é obrigatório"

  Cenario: 04 - Cadastrar Associado Com CPF Inválido
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF "45008224241"
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O CPF do associado é inválido"

  Cenario: 05 - Cadastrar Associado Com CPF Já Cadastrado
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF "78186436057"
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O CPF informado já está cadatrado"