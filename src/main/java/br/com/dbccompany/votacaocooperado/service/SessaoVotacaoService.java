package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;

import java.util.List;

public interface SessaoVotacaoService {

    List<SessaoVotacao> listarTodos();

    SessaoVotacao cadastrar(SessaoVotacao sessaoVotacao);
}
