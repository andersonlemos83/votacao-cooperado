package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.domain.Voto;

import java.util.List;

public interface VotoService {

    List<Voto> listarTodos();

    Voto cadastrar(Voto voto);
}