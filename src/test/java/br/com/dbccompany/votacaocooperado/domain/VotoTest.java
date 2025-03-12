package br.com.dbccompany.votacaocooperado.domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.NAO;
import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.SIM;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class VotoTest {

    private Voto voto;

    @BeforeEach
    public void inicializarContexto() {
        voto = Instancio.create(Voto.class);
    }

    @Test
    public void aoObterIdAssociadoDadoQueAssociadoEstejaNuloDeveriaRetornarIdAssociadoNulo() {
        voto.setAssociado(null);
        assertNull(voto.obterIdAssociado());
    }

    @Test
    public void aoObterIdAssociadoDadoQueAssociadoNaoEstejaNuloDeveriaRetornarIdAssociadoEsperado() {
        Associado associadoEsperado = Instancio.create(Associado.class);
        voto.setAssociado(associadoEsperado);

        Long idAssociadoRetornado = voto.obterIdAssociado();

        assertEquals(associadoEsperado.getId(), idAssociadoRetornado);
    }

    @Test
    public void aoObterIdAssembleiaDadoQueAssembleiaEstejaNulaDeveriaRetornarIdAssembleiaNulo() {
        voto.setAssembleia(null);
        assertNull(voto.obterIdAssembleia());
    }

    @Test
    public void aoObterIdAssembleiaDadoQueAssembleiaNaoEstejaNulaDeveriaRetornarIdAssembleiaEsperado() {
        Assembleia assembleiaEsperada = Instancio.create(Assembleia.class);
        voto.setAssembleia(assembleiaEsperada);

        Long idAssembleiaRetornado = voto.obterIdAssembleia();

        assertEquals(assembleiaEsperada.getId(), idAssembleiaRetornado);
    }

    @Test
    public void aoChamarMetodoEhSimDadoQueTipoVotoSejaSimDeveriaRetornarVerdadeiro() {
        voto.setTipoVoto(SIM);
        assertTrue("Deveria retornar verdadeiro", voto.ehSim());
    }

    @Test
    public void aoChamarMetodoEhSimDadoQueTipoVotoSejaNaoDeveriaRetornarFalso() {
        voto.setTipoVoto(NAO);
        assertFalse("Deveria retornar falso", voto.ehSim());
    }

    @Test
    public void aoChamarMetodoEhNaoDadoQueTipoVotoSejaNaoDeveriaRetornarVerdadeiro() {
        voto.setTipoVoto(NAO);
        assertTrue("Deveria retornar verdadeiro", voto.ehNao());
    }

    @Test
    public void aoChamarMetodoEhNaoDadoQueTipoVotoSejaSimDeveriaRetornarFalso() {
        voto.setTipoVoto(SIM);
        assertFalse("Deveria retornar falso", voto.ehNao());
    }
}