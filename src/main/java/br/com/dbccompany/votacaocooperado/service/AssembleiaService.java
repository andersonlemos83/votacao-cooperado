package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;

import java.util.List;

public interface AssembleiaService {

    List<Assembleia> listarTodos();

    Assembleia cadastrar(Assembleia assembleia);
}
