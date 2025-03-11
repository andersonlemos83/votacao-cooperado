package br.com.dbccompany.votacaocooperado.domain;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.builder.VotoBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class VotoTest {

    private Voto voto;

    @Before
    public void inicializarContexto() {
        voto = VotoBuilder.umVotoQualquer().build();
    }

    @Test
    public void aoObterIdAssociadoDadoQueAssociadoEstejaNuloDeveriaRetornarIdAssociadoNulo() {
        voto.setAssociado(null);
        assertNull(voto.obterIdAssociado());
    }

    @Test
    public void aoObterIdAssociadoDadoQueAssociadoNaoEstejaNuloDeveriaRetornarIdAssociadoEsperado() {
        voto.setAssociado(AssociadoBuilder.umAssociadoQualquer().build());

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
        voto.setAssembleia(AssembleiaBuilder.umaAssembleiaQualquer().build());

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