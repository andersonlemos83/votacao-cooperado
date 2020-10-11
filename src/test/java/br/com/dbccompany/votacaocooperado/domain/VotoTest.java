package br.com.dbccompany.votacaocooperado.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class VotoTest {

    private Voto voto;

    @Before
    public void inicializarContexto() {
        voto = new Voto();
    }

    @Test
    public void aoObterIdAssociadoDadoQueAssociadoEstejaNuloDeveriaRetornarIdAssociadoNulo() {
        voto.setAssociado(null);
        assertNull(voto.obterIdAssociado());
    }

    @Test
    public void aoObterIdAssociadoDadoQueAssociadoNaoEstejaNuloDeveriaRetornarIdAssociadoEsperado() {
        voto.setAssociado(new Associado(1l));

        Long idAssociadoRetornado = voto.obterIdAssociado();

        assertEquals(Long.valueOf(1), idAssociadoRetornado);
    }

    @Test
    public void aoObterIdSessaoVotacaoDadoQueSessaoVotacaoEstejaNulaDeveriaRetornarIdSessaoVotacaoNulo() {
        voto.setSessaoVotacao(null);
        assertNull(voto.obterIdSessaoVotacao());
    }

    @Test
    public void aoObterIdSessaoVotacaoDadoQueSessaoVotacaoNaoEstejaNulaDeveriaRetornarIdSessaoVotacaoEsperado() {
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setId(1l);
        voto.setSessaoVotacao(sessaoVotacao);

        Long idSessaoVotacaoRetornado = voto.obterIdSessaoVotacao();

        assertEquals(Long.valueOf(1), idSessaoVotacaoRetornado);
    }

    @Test
    public void aoChamarMetodoEhSimDadoQueTipoVotoSejaSimDeveriaRetornarVerdadeiro() {
        voto.setTipoVoto(TipoVoto.SIM);
        assertTrue("Deveria retornar verdadeiro", voto.ehSim());
    }

    @Test
    public void aoChamarMetodoEhSimDadoQueTipoVotoSejaNaoDeveriaRetornarFalso() {
        voto.setTipoVoto(TipoVoto.NAO);
        assertFalse("Deveria retornar falso", voto.ehSim());
    }

    @Test
    public void aoChamarMetodoEhNaoDadoQueTipoVotoSejaNaoDeveriaRetornarVerdadeiro() {
        voto.setTipoVoto(TipoVoto.NAO);
        assertTrue("Deveria retornar verdadeiro", voto.ehNao());
    }

    @Test
    public void aoChamarMetodoEhNaoDadoQueTipoVotoSejaSimDeveriaRetornarFalso() {
        voto.setTipoVoto(TipoVoto.SIM);
        assertFalse("Deveria retornar falso", voto.ehNao());
    }
}