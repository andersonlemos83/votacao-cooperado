package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.domain.Associado;

import java.util.List;

public interface AssociadoService {

    List<Associado> listarTodos();

    Associado cadastrar(Associado associado);
}