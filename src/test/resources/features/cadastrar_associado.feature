# language: pt

Funcionalidade: Cadastrar Associado

  Cenario de Fundo:
    Dado que existam os associados cadastrados
      | Nome        | cpf         |
      | Klaus Meine | 78186436057 |

  Cenario: 01 - Cadastrar Associado Com Sucesso
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF "08699056001"
    E que existam os seguintes responses disponiveis no endpoint findByCpf
      | Key         | Status | Response      |
      | 08699056001 | OK     | Laura Pausini |
    Quando cadastrar associado
    Entao deveria cadastrar o seguinte associado
      | Nome          | cpf         |
      | Laura Pausini | 08699056001 |

  Cenario: 02 - Cadastrar Associado Sem Informar Nome
    Dado que seja informado o nome ""
    E que seja informado o CPF "08699056001"
    E que existam os seguintes responses disponiveis no endpoint findByCpf
      | Key         | Status | Response      |
      | 08699056001 | OK     | Laura Pausini |
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O nome do associado é obrigatório"

  Cenario: 03 - Cadastrar Associado Sem Informar CPF
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF ""
    E que existam os seguintes responses disponiveis no endpoint findByCpf
      | Key         | Status | Response      |
      | 08699056001 | OK     | Laura Pausini |
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O CPF do associado é obrigatório"

  Cenario: 04 - Cadastrar Associado Com CPF Inválido
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF "45008224241"
    E que existam os seguintes responses disponiveis no endpoint findByCpf
      | Key         | Status      | Response             |
      | 45008224241 | BAD_REQUEST | Usuario inexistente! |
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O CPF do associado é inválido"

  Cenario: 05 - Cadastrar Associado Com CPF Já Cadastrado
    Dado que seja informado o nome "Laura Pausini"
    E que seja informado o CPF "78186436057"
    E que existam os seguintes responses disponiveis no endpoint findByCpf
      | Key         | Status | Response    |
      | 78186436057 | OK     | Klaus Meine |
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O CPF informado já está cadastrado"

  Cenario: 06 - Cadastrar Associado Com Servico de Validacao de CPF Offline
    Dado que seja informado o nome "Russell Hitchcock"
    E que seja informado o CPF "61022326074"
    Quando cadastrar associado
    Entao deveria retornar a mensagem "O serviço de validação do CPF está offline"