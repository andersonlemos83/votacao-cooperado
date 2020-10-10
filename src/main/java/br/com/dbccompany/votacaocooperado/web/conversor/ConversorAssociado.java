package br.com.dbccompany.votacaocooperado.web.conversor;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;

import javax.validation.Valid;
import java.util.List;

public interface ConversorAssociado {

    Associado converter(@Valid AssociadoDto associadoDto);

    List<AssociadoDto> converter(List<Associado> associados);

    AssociadoDto converter(Associado associado);
}