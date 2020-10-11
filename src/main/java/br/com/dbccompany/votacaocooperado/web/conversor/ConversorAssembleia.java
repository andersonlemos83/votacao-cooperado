package br.com.dbccompany.votacaocooperado.web.conversor;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;

import java.util.List;

public interface ConversorAssembleia {

    Assembleia converter(AssembleiaDto assembleiaDto);

    List<AssembleiaDto> converter(List<Assembleia> assembleias);

    AssembleiaDto converter(Assembleia assembleia);
}