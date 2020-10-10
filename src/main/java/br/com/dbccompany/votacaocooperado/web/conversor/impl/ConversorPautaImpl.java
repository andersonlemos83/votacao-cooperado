package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorPauta;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConversorPautaImpl implements ConversorPauta {
    @Override
    public Pauta converter(PautaDto pautaDto) {
        Pauta pauta = new Pauta();
        pauta.setId(pautaDto.getId());
        pauta.setDescricao(pautaDto.getDescricao());
        return pauta;
    }

    @Override
    public List<PautaDto> converter(List<Pauta> pautas) {
        List<PautaDto> pautasDto = new ArrayList<>();
        for (Pauta pauta : pautas) {
            pautasDto.add(converter(pauta));
        }
        return pautasDto;
    }

    @Override
    public PautaDto converter(Pauta pauta) {
        PautaDto pautaDto = new PautaDto();
        pautaDto.setId(pauta.getId());
        pautaDto.setDescricao(pauta.getDescricao());
        return pautaDto;
    }
}