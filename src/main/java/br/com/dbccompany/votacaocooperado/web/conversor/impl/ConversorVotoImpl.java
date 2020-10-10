package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConversorVotoImpl implements ConversorVoto {
    @Override
    public Voto converter(VotoDto votoDto) {
        Voto voto = new Voto();
        voto.setId(votoDto.getId());
        voto.setTipoVoto(votoDto.getTipoVoto());
        voto.setAssociado(new Associado(votoDto.getIdAssociado()));
        voto.setSessaoVotacao(new SessaoVotacao(votoDto.getIdSessaoVotacao()));
        return voto;
    }

    @Override
    public List<VotoDto> converter(List<Voto> votos) {
        if (votos == null) {
            return new ArrayList<>();
        }
        List<VotoDto> votosDto = new ArrayList<>();
        for (Voto voto : votos) {
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
        votoDto.setIdSessaoVotacao(voto.obterIdSessaoVotacao());
        return votoDto;
    }
}
