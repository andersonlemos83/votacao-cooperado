package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorPauta;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class ConversorPautaImplTest {

    private ConversorPauta conversorPauta;

    private Pauta pauta;
    private PautaDto pautaDto;
    private PautaConsolidadaDto pautaConsolidadaDto;
    private Date dataCriacao;

    @Before
    public void inicializarContexto() {
        conversorPauta = new ConversorPautaImpl();

        dataCriacao = new Date();
        pauta = gerarPauta();
        pautaDto = gerarPautaDto();
        pautaConsolidadaDto = gerarPautaConsolidadaDto();
    }

    @Test
    public void aoConverterParaEntidadeDeveriaRetonarAhEntidadeEsperada() {
        Pauta pautaRetornada = conversorPauta.converter(pautaDto);

        assertEquals(pauta.getId(), pautaRetornada.getId());
        assertEquals(pauta.getDescricao(), pautaRetornada.getDescricao());
    }

    @Test
    public void aoConverterParaListaDtoDeveriaRetonarAhListaDtoEsperada() {
        List<PautaDto> pautasDto = conversorPauta.converter(Arrays.asList(pauta));

        assertFalse(pautasDto.isEmpty());

        PautaDto pautaDtoRetornado = pautasDto.get(0);
        assertEquals(pautaDto.getId(), pautaDtoRetornado.getId());
        assertEquals(pautaDto.getDescricao(), pautaDtoRetornado.getDescricao());
    }

    @Test
    public void aoConverterParaConsolidadaDtoDeveriaRetonarAhPautaConsolidadaDtoEsperada() {
        PautaConsolidadaDto pautaConsolidadaDtoRetornado = conversorPauta.converterParaConsolidada(pauta);

        assertEquals(pautaConsolidadaDto.getDescricao(), pautaConsolidadaDtoRetornado.getDescricao());
        assertEquals(pautaConsolidadaDto.getDataCriacao(), pautaConsolidadaDtoRetornado.getDataCriacao());
        assertEquals(pautaConsolidadaDto.getStatusAssembleia(), pautaConsolidadaDtoRetornado.getStatusAssembleia());
        assertEquals(pautaConsolidadaDto.getQuantidadeVotosSim(), pautaConsolidadaDtoRetornado.getQuantidadeVotosSim());
        assertEquals(pautaConsolidadaDto.getQuantidadeVotosNao(), pautaConsolidadaDtoRetornado.getQuantidadeVotosNao());
    }

    private Pauta gerarPauta() {
        Pauta pauta = new Pauta();
        pauta.setId(1l);
        pauta.setDescricao("Votar sobre novo fundo de investimento.");
        pauta.setAssembleias(gerarAssembleias());
        return pauta;
    }

    private List<Assembleia> gerarAssembleias() {
        return Arrays.asList(gerarAssembleia());
    }

    private Assembleia gerarAssembleia() {
        Assembleia assembleia = new Assembleia();
        assembleia.setDataCriacao(dataCriacao);
        assembleia.setTempoDuracao(1);
        assembleia.setVotos(gerarVotos());
        return assembleia;
    }

    private List<Voto> gerarVotos() {
        return Arrays.asList(gerarVoto());
    }

    private Voto gerarVoto() {
        Voto voto = new Voto();
        voto.setTipoVoto(TipoVoto.SIM);
        return voto;
    }

    private PautaDto gerarPautaDto() {
        PautaDto pautaDto = new PautaDto();
        pautaDto.setId(1l);
        pautaDto.setDescricao("Votar sobre novo fundo de investimento.");
        return pautaDto;
    }

    private PautaConsolidadaDto gerarPautaConsolidadaDto() {
        PautaConsolidadaDto pautaConsolidadaDto = new PautaConsolidadaDto();
        pautaConsolidadaDto.setDescricao("Votar sobre novo fundo de investimento.");
        pautaConsolidadaDto.setDataCriacao(dataCriacao);
        pautaConsolidadaDto.setStatusAssembleia(ABERTA);
        pautaConsolidadaDto.setQuantidadeVotosSim(1);
        pautaConsolidadaDto.setQuantidadeVotosNao(0);
        return pautaConsolidadaDto;
    }
}
