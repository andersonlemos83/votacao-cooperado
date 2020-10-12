package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssembleia;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
public class ConversorAssembleiaImpl implements ConversorAssembleia {

    private final ConversorVoto conversorVoto;

    public ConversorAssembleiaImpl(ConversorVoto conversorVoto) {
        this.conversorVoto = conversorVoto;
    }

    @Override
    public Assembleia converter(AssembleiaDto assembleiaDto) {
        Assembleia assembleia = new Assembleia();
        assembleia.setId(assembleiaDto.getId());
        assembleia.setTempoDuracao(assembleiaDto.getTempoDuracao());
        assembleia.setPauta(new Pauta(assembleiaDto.getIdPauta()));
        return assembleia;
    }

    @Override
    public List<AssembleiaDto> converter(List<Assembleia> assembleias) {
        List<AssembleiaDto> assembleiasDto = new ArrayList<>();
        for (Assembleia assembleia : Optional.ofNullable(assembleias).orElse(emptyList())) {
            assembleiasDto.add(converter(assembleia));
        }
        return assembleiasDto;
    }

    @Override
    public AssembleiaDto converter(Assembleia assembleia) {
        AssembleiaDto assembleiaDto = new AssembleiaDto();
        assembleiaDto.setId(assembleia.getId());
        assembleiaDto.setDataCriacao(assembleia.getDataCriacao());
        assembleiaDto.setTempoDuracao(assembleia.getTempoDuracao());
        assembleiaDto.setIdPauta(assembleia.obterIdPauta());
        assembleiaDto.setVotos(conversorVoto.converter(assembleia.getVotos()));
        return assembleiaDto;
    }
}
