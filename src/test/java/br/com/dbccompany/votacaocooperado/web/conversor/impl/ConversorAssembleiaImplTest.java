package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssembleia;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ConversorAssembleiaImplTest {

    private ConversorAssembleia conversorAssembleia;

    @Mock
    private ConversorVoto conversorVotoMock;

    private Assembleia assembleia;
    private AssembleiaDto assembleiaDto;
    private Date dataCriacao;
    private List<Voto> votos;
    private List<VotoDto> votosDto;

    @Before
    public void inicializarContexto() {
        conversorAssembleia = new ConversorAssembleiaImpl(conversorVotoMock);

        dataCriacao = new Date();
        votos = new ArrayList<>();
        votosDto = new ArrayList<>();
        assembleia = gerarAssembleia();
        assembleiaDto = gerarAssembleiaDto();
    }

    @Test
    public void aoConverterParaEntidadeDeveriaRetonarAhEntidadeEsperada() {
        Assembleia assembleiaRetornada = conversorAssembleia.converter(assembleiaDto);

        assertEquals(assembleia.getId(), assembleiaRetornada.getId());
        assertEquals(assembleia.getTempoDuracao(), assembleiaRetornada.getTempoDuracao());
        assertEquals(assembleia.obterIdPauta(), assembleiaRetornada.obterIdPauta());
    }

    @Test
    public void aoConverterParaListaDtoDeveriaRetonarAhListaDtoEsperada() {
        Mockito.when(conversorVotoMock.converter(votos)).thenReturn(votosDto);

        List<AssembleiaDto> assembleiasDto = conversorAssembleia.converter(Arrays.asList(assembleia));

        assertFalse(assembleiasDto.isEmpty());

        AssembleiaDto assembleiaDtoRetornado = assembleiasDto.get(0);
        assertEquals(assembleiaDto.getId(), assembleiaDtoRetornado.getId());
        assertEquals(assembleiaDto.getDataCriacao(), assembleiaDtoRetornado.getDataCriacao());
        assertEquals(assembleiaDto.getTempoDuracao(), assembleiaDtoRetornado.getTempoDuracao());
        assertEquals(assembleiaDto.getIdPauta(), assembleiaDtoRetornado.getIdPauta());
        assertEquals(assembleiaDto.getVotos(), assembleiaDtoRetornado.getVotos());
    }

    private Assembleia gerarAssembleia() {
        Assembleia assembleia = new Assembleia();
        assembleia.setId(1l);
        assembleia.setDataCriacao(dataCriacao);
        assembleia.setTempoDuracao(4);
        assembleia.setPauta(new Pauta(2l));
        assembleia.setVotos(votos);
        return assembleia;
    }

    private AssembleiaDto gerarAssembleiaDto() {
        AssembleiaDto assembleiaDto = new AssembleiaDto();
        assembleiaDto.setId(1l);
        assembleiaDto.setDataCriacao(dataCriacao);
        assembleiaDto.setTempoDuracao(4);
        assembleiaDto.setIdPauta(2l);
        assembleiaDto.setVotos(votosDto);
        return assembleiaDto;
    }
}