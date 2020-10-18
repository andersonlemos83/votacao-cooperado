package br.com.dbccompany.votacaocooperado.domain;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.DataHoraBuilder;
import br.com.dbccompany.votacaocooperado.builder.PautaBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.com.dbccompany.votacaocooperado.builder.VotoBuilder.umVotoQualquerNao;
import static br.com.dbccompany.votacaocooperado.builder.VotoBuilder.umVotoQualquerSim;
import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;
import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.FECHADA;
import static br.com.dbccompany.votacaocooperado.util.AssertUtil.assertData;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AssembleiaTest {

    private Assembleia assembleia;

    @Before
    public void inicializarContexto() {
        assembleia = AssembleiaBuilder.umaAssembleiaQualquer().build();
    }

    @Test
    public void aoObterIdPautaDadoQuePautaEstejaNulaDeveriaRetornarIdPautaNulo() {
        assembleia.setPauta(null);
        assertNull(assembleia.obterIdPauta());
    }

    @Test
    public void aoObterIdPautaDadoQuePautaNaoEstejaNulaDeveriaRetornarIdPautaEsperado() {
        assembleia.setPauta(PautaBuilder.umaPautaQualquer().build());

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

    @Test
    public void aoObterStatusAssembleiaDadoQueDataCriacaoEstejaNulaDeveriaRetornarFechada() {
        assembleia.setDataCriacao(null);
        assembleia.setTempoDuracao(1);
        assertEquals(FECHADA, assembleia.obterStatusAssembleia());
    }

    @Test
    public void aoObterStatusAssembleiaDadoQueVotacaoEstejaExpiradaDeveriaRetornarFechada() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(1);
        assertEquals(FECHADA, assembleia.obterStatusAssembleia());
    }

    @Test
    public void aoObterStatusAssembleiaDadoQueVotacaoNaoEstejaExpiradaDeveriaRetornarAberta() {
        assembleia.setDataCriacao(new Date());
        assembleia.setTempoDuracao(1);
        assertEquals(ABERTA, assembleia.obterStatusAssembleia());
    }

    @Test
    public void aoChamarMetodoPrePersistDadoQueDataCriacaoIhTempoDuracaoEstejamInvalidosDeveriaConfigurarValoresPadrao() {
        assembleia.setDataCriacao(null);
        assembleia.setTempoDuracao(0);

        assembleia.prePersist();

        assertData(new Date(), assembleia.getDataCriacao());
        assertEquals(1, assembleia.getTempoDuracao());
    }

    @Test
    public void aoChamarMetodoPrePersistDadoQueDataCriacaoIhTempoDuracaoEstejamValidosDeveriaManterOsValoresAtuais() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(15);

        assembleia.prePersist();

        assertData(obterDataCriacaoExpirada(), assembleia.getDataCriacao());
        assertEquals(15, assembleia.getTempoDuracao());
    }

    private Date obterDataCriacaoExpirada() {
        return DataHoraBuilder.umaData().nMinutosAtras(30).build();
    }

    private List<Voto> gerarListaComQuatroVotosSimIhSeisVotosNao() {
        return Arrays.asList(umVotoQualquerSim().build(), umVotoQualquerSim().build(), umVotoQualquerSim().build(),
                umVotoQualquerSim().build(), umVotoQualquerNao().build(), umVotoQualquerNao().build(),
                umVotoQualquerNao().build(), umVotoQualquerNao().build(), umVotoQualquerNao().build(),
                umVotoQualquerNao().build());
    }
}