package br.com.dbccompany.votacaocooperado.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.MINUTE;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AssembleiaTest {

    private Assembleia assembleia;

    @Before
    public void inicializarContexto() {
        assembleia = new Assembleia();
    }

    @Test
    public void aoObterIdPautaDadoQuePautaEstejaNulaDeveriaRetornarIdPautaNulo() {
        assembleia.setPauta(null);
        assertNull(assembleia.obterIdPauta());
    }

    @Test
    public void aoObterIdPautaDadoQuePautaNaoEstejaNulaDeveriaRetornarIdPautaEsperado() {
        assembleia.setPauta(new Pauta(1l));

        Long idPautaRetornado = assembleia.obterIdPauta();

        assertEquals(Long.valueOf(1), idPautaRetornado);
    }

    @Test
    public void aoChamarMetodoEstaFechadaDadoQueVotacaoEstejaExpiradaDeveriaRetornarVerdadeiro() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(1);
        assertTrue("Deveria retornar verdadeiro", assembleia.estaFechada());
    }

    @Test
    public void aoChamarMetodoEstaFechadaDadoQueVotacaoNaoEstejaExpiradaDeveriaRetornarFalso() {
        assembleia.setDataCriacao(new Date());
        assembleia.setTempoDuracao(1);
        assertFalse("Deveria retornar falso", assembleia.estaFechada());
    }

    @Test
    public void aoObterQuantidadeVotosSimDadoQueAhAssembleiaContenhaQuatroVotosSimIhSeisVotosNaoDeveriaRetornarQuatro() {
        assembleia.setVotos(gerarListaComQuatroVotosSimIhSeisVotosNao());

        int quantidadeVotosSim = assembleia.obterQuantidadeVotosSim();

        assertTrue("Deveria retornar 4", quantidadeVotosSim == 4);
    }

    @Test
    public void aoObterQuantidadeVotosNaoDadoQueAhAssembleiaContenhaQuatroVotosSimIhSeisVotosNaoDeveriaRetornarSeis() {
        assembleia.setVotos(gerarListaComQuatroVotosSimIhSeisVotosNao());

        int quantidadeVotosNao = assembleia.obterQuantidadeVotosNao();

        assertTrue("Deveria retornar 6", quantidadeVotosNao == 6);
    }

    private Date obterDataCriacaoExpirada() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(MINUTE, -30);
        return calendar.getTime();
    }

    private List<Voto> gerarListaComQuatroVotosSimIhSeisVotosNao() {
        return Arrays.asList(gerarVotoSim(), gerarVotoSim(), gerarVotoSim(), gerarVotoSim(), gerarVotoNao(),
                gerarVotoNao(), gerarVotoNao(), gerarVotoNao(), gerarVotoNao(), gerarVotoNao());
    }

    private Voto gerarVotoSim() {
        Voto voto = new Voto();
        voto.setTipoVoto(TipoVoto.SIM);
        return voto;
    }

    private Voto gerarVotoNao() {
        Voto voto = new Voto();
        voto.setTipoVoto(TipoVoto.NAO);
        return voto;
    }
}