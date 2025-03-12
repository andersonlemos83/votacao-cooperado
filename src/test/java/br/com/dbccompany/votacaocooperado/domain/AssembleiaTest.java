package br.com.dbccompany.votacaocooperado.domain;

import br.com.dbccompany.votacaocooperado.helper.builder.DataHoraBuilder;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;
import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.FECHADA;
import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.NAO;
import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.SIM;
import static br.com.dbccompany.votacaocooperado.helper.util.AssertUtil.assertData;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class AssembleiaTest {

    private Assembleia assembleia;

    @BeforeEach
    public void inicializarContexto() {
        assembleia = Instancio.create(Assembleia.class);
    }

    @Test
    public void aoObterIdPautaDadoQuePautaEstejaNulaDeveriaRetornarIdPautaNulo() {
        assembleia.setPauta(null);
        assertNull(assembleia.obterIdPauta());
    }

    @Test
    public void aoObterIdPautaDadoQuePautaNaoEstejaNulaDeveriaRetornarIdPautaEsperado() {
        Pauta pautaEsperada = Instancio.create(Pauta.class);
        assembleia.setPauta(pautaEsperada);

        Long idPautaRetornado = assembleia.obterIdPauta();

        assertEquals(pautaEsperada.getId(), idPautaRetornado);
    }

    @Test
    public void aoChamarMetodoEstaFechadaDadoQueVotacaoEstejaExpiradaDeveriaRetornarVerdadeiro() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(1);
        assertTrue("Deveria retornar verdadeiro", assembleia.estaFechada());
    }

    @Test
    public void aoChamarMetodoEstaFechadaDadoQueDataCriacaoEstejaNulaDeveriaRetornarVerdadeiro() {
        assembleia.setDataCriacao(null);
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
        return Arrays.asList(gerarVotoSim(), gerarVotoSim(), gerarVotoSim(), gerarVotoSim(),
                gerarVotoNao(), gerarVotoNao(), gerarVotoNao(), gerarVotoNao(), gerarVotoNao(),
                gerarVotoNao());
    }

    private Voto gerarVotoSim() {
        return Instancio.of(Voto.class).set(Select.field("tipoVoto"), SIM).create();
    }

    private Voto gerarVotoNao() {
        return Instancio.of(Voto.class).set(Select.field("tipoVoto"), NAO).create();
    }
}