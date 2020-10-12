package br.com.dbccompany.votacaocooperado.service.validador;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;

public interface ValidadorAssembleia {

    void validar(Long idAssembleia);

    void validar(Assembleia assembleia);
}