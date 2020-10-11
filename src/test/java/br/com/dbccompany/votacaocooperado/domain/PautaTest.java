package br.com.dbccompany.votacaocooperado.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PautaTest {

    private Pauta pauta;
    private SessaoVotacao sessaoVotacaoComDataMaisRecente;

    @Before
    public void inicializarContexto() {
        pauta = new Pauta();
    }

    @Test
    public void aoObterUltimaSessaoVotacaoDadoQueExistamTresSessoesOrdenadasDeveriaRetonarAhUltimaSessaoVotacao() {
        pauta.setSessoesVotacao(gerarTresSessoesVotacao());

        SessaoVotacao ultimaSessaoVotacao = pauta.obterUltimaSessaoVotacao();

        assertEquals(sessaoVotacaoComDataMaisRecente, ultimaSessaoVotacao);
    }

    private List<SessaoVotacao> gerarTresSessoesVotacao() {
        sessaoVotacaoComDataMaisRecente = gerarSessaoVotacao(1);
        return Arrays.asList(gerarSessaoVotacao(21), gerarSessaoVotacao(11), sessaoVotacaoComDataMaisRecente);
    }

    private SessaoVotacao gerarSessaoVotacao(int minutos) {
        Calendar dataCriacaoCalendar = Calendar.getInstance();
        dataCriacaoCalendar.add(Calendar.MINUTE, -minutos);

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setId(Long.valueOf(minutos));
        sessaoVotacao.setDataCriacao(dataCriacaoCalendar.getTime());
        return sessaoVotacao;
    }
}