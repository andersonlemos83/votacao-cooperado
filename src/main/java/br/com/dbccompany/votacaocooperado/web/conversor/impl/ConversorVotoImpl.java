package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
public class ConversorVotoImpl implements ConversorVoto {
    @Override
    public Voto converter(VotoDto votoDto) {
        Voto voto = new Voto();
        voto.setId(votoDto.getId());
        voto.setTipoVoto(votoDto.getTipoVoto());
        voto.setAssociado(new Associado(votoDto.getIdAssociado()));
        voto.setAssembleia(new Assembleia(votoDto.getIdAssembleia()));
        return voto;
    }

    @Override
    public List<VotoDto> converter(List<Voto> votos) {
        List<VotoDto> votosDto = new ArrayList<>();
        for (Voto voto : Optional.ofNullable(votos).orElse(emptyList())) {
            votosDto.add(converter(voto));
        }
        return votosDto;
    }

    @Override
    public VotoDto converter(Voto voto) {
        VotoDto votoDto = new VotoDto();
        votoDto.setId(voto.getId());
        votoDto.setTipoVoto(voto.getTipoVoto());
        votoDto.setIdAssociado(voto.obterIdAssociado());
        votoDto.setIdAssembleia(voto.obterIdAssembleia());
        return votoDto;
    }
}
