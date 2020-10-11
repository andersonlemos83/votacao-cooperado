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
    public void aoObterIdAssembleiaDadoQueAssembleiaEstejaNulaDeveriaRetornarIdAssembleiaNulo() {
        voto.setAssembleia(null);
        assertNull(voto.obterIdAssembleia());
    }

    @Test
    public void aoObterIdAssembleiaDadoQueAssembleiaNaoEstejaNulaDeveriaRetornarIdAssembleiaEsperado() {
        Assembleia assembleia = new Assembleia();
        assembleia.setId(1l);
        voto.setAssembleia(assembleia);

        Long idAssembleiaRetornado = voto.obterIdAssembleia();

        assertEquals(Long.valueOf(1), idAssembleiaRetornado);
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