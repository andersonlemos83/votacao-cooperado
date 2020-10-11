package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConversorVotoImplTest {

    private ConversorVoto conversorVoto;

    private Voto voto;
    private VotoDto votoDto;

    @Before
    public void inicializarContexto() {
        conversorVoto = new ConversorVotoImpl();

        voto = gerarVoto();
        votoDto = gerarVotoDto();
    }

    @Test
    public void aoConverterParaEntidadeDeveriaRetonarAhEntidadeEsperada() {
        Voto votoRetornada = conversorVoto.converter(votoDto);

        assertEquals(voto.getId(), votoRetornada.getId());
        assertEquals(voto.getTipoVoto(), votoRetornada.getTipoVoto());
        assertEquals(voto.obterIdAssembleia(), votoRetornada.obterIdAssembleia());
        assertEquals(voto.obterIdAssociado(), votoRetornada.obterIdAssociado());
    }

    @Test
    public void aoConverterParaListaDtoDeveriaRetonarAhListaDtoEsperada() {
        List<VotoDto> votosDto = conversorVoto.converter(Arrays.asList(voto));

        assertFalse(votosDto.isEmpty());

        VotoDto votoDtoRetornado = votosDto.get(0);
        assertEquals(votoDto.getId(), votoDtoRetornado.getId());
        assertEquals(votoDto.getTipoVoto(), votoDtoRetornado.getTipoVoto());
        assertEquals(votoDto.getIdAssembleia(), votoDtoRetornado.getIdAssembleia());
        assertEquals(votoDto.getIdAssociado(), votoDtoRetornado.getIdAssociado());
    }

    private Voto gerarVoto() {
        Voto voto = new Voto();
        voto.setId(1l);
        voto.setTipoVoto(TipoVoto.SIM);
        voto.setAssociado(new Associado(2l));
        voto.setAssembleia(new Assembleia(3l));
        return voto;
    }

    private VotoDto gerarVotoDto() {
        VotoDto votoDto = new VotoDto();
        votoDto.setId(1l);
        votoDto.setTipoVoto(TipoVoto.SIM);
        votoDto.setIdAssociado(2l);
        votoDto.setIdAssembleia(3l);
        return votoDto;
    }
}