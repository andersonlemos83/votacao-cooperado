package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.domain.Pauta;

import java.util.List;

public interface PautaService {

    List<Pauta> listarTodos();

    Pauta cadastrar(Pauta pauta);

    Pauta buscarPorId(Long id);
}