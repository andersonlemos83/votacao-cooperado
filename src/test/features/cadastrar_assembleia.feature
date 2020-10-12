# language: pt

Funcionalidade: Cadastrar Assembleia

  Cenario de Fundo:
    Dado que existam as pautas cadastradas
      | id | Descricao                               |
      | 1  | Emissão de novas cotas do fundo Musical |
      | 2  | Criação de novo fundo de investimento   |

  Cenario: 01 - Cadastrar Assembleia Com Sucesso
    Dado que seja informado o tempo de duracao de "5" minutos
    E que seja informada a pauta "1"
    Quando cadastrar assembleia
    Entao deveria cadastrar a seguinte assembleia
      | Descricao Pauta                         | Tempo Duracao | Data Criacao |
      | Emissão de novas cotas do fundo Musical | 5             | DATA_ATUAL   |

#  Cenario: 02 - Cadastrar Assembleia Com Sucesso Sem Informar o Tempo de Duracao
#    Dado que seja informado o tempo de duracao de "" minutos
#    E que seja informada a pauta "2"
#    Quando cadastrar assembleia
#    Entao deveria cadastrar a seguinte assembleia
#      | Descricao Pauta                       | Tempo Duracao | Data Criacao |
#      | Criação de novo fundo de investimento | 1             | DATA_ATUAL   |

  Cenario: 03 - Cadastrar Assembleia Sem Informar Pauta
    Dado que seja informado o tempo de duracao de "5" minutos
    E que seja informada a pauta ""
    Quando cadastrar assembleia
    Entao deveria retornar a mensagem "O ID da pauta é obrigatório"

  Cenario: 04 - Cadastrar Assembleia Informando Pauta Inexistente
    Dado que seja informado o tempo de duracao de "5" minutos
    E que seja informada a pauta "11"
    Quando cadastrar assembleia
    Entao deveria retornar a mensagem "A pauta informada não existe"