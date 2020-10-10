package br.com.dbccompany.votacaocooperado.web.conversor;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;

import java.util.List;

public interface ConversorPauta {

    Pauta converter(PautaDto pautaDto);

    List<PautaDto> converter(List<Pauta> pautas);

    PautaDto converter(Pauta pauta);

    PautaConsolidadaDto converterParaConsolidada(Pauta pauta);
}