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
public class SessaoVotacaoTest {

    private SessaoVotacao sessaoVotacao;

    @Before
    public void inicializarContexto() {
        sessaoVotacao = new SessaoVotacao();
    }

    @Test
    public void aoObterIdPautaDadoQuePautaEstejaNulaDeveriaRetornarIdPautaNulo() {
        sessaoVotacao.setPauta(null);
        assertNull(sessaoVotacao.obterIdPauta());
    }

    @Test
    public void aoObterIdPautaDadoQuePautaNaoEstejaNulaDeveriaRetornarIdPautaEsperado() {
        sessaoVotacao.setPauta(new Pauta(1l));

        Long idPautaRetornado = sessaoVotacao.obterIdPauta();

        assertEquals(Long.valueOf(1), idPautaRetornado);
    }

    @Test
    public void aoChamarMetodoEstaFechadaDadoQueVotacaoEstejaExpiradaDeveriaRetornarVerdadeiro() {
        sessaoVotacao.setDataCriacao(obterDataCriacaoExpirada());
        sessaoVotacao.setTempoDuracao(1);
        assertTrue("Deveria retornar verdadeiro", sessaoVotacao.estaFechada());
    }

    @Test
    public void aoChamarMetodoEstaFechadaDadoQueVotacaoNaoEstejaExpiradaDeveriaRetornarFalso() {
        sessaoVotacao.setDataCriacao(new Date());
        sessaoVotacao.setTempoDuracao(1);
        assertFalse("Deveria retornar falso", sessaoVotacao.estaFechada());
    }

    @Test
    public void aoObterQuantidadeVotosSimDadoQueAhSessaoContenhaQuatroVotosSimIhSeisVotosNaoDeveriaRetornarQuatro() {
        sessaoVotacao.setVotos(gerarListaComQuatroVotosSimIhSeisVotosNao());

        int quantidadeVotosSim = sessaoVotacao.obterQuantidadeVotosSim();

        assertTrue("Deveria retornar 4", quantidadeVotosSim == 4);
    }

    @Test
    public void aoObterQuantidadeVotosNaoDadoQueAhSessaoContenhaQuatroVotosSimIhSeisVotosNaoDeveriaRetornarSeis() {
        sessaoVotacao.setVotos(gerarListaComQuatroVotosSimIhSeisVotosNao());

        int quantidadeVotosNao = sessaoVotacao.obterQuantidadeVotosNao();

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