package br.com.dbccompany.votacaocooperado.web.conversor;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;

import java.util.List;

public interface ConversorVoto {

    Voto converter(VotoDto votoDto);

    List<VotoDto> converter(List<Voto> votos);

    VotoDto converter(Voto voto);
}