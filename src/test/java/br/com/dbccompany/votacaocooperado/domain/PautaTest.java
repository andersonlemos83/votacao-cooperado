package br.com.dbccompany.votacaocooperado.domain;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.DataHoraBuilder;
import br.com.dbccompany.votacaocooperado.builder.PautaBuilder;
import br.com.dbccompany.votacaocooperado.builder.VotoBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PautaTest {

    private Pauta pauta;
    private Assembleia assembleiaAtual;

    @Before
    public void inicializarContexto() {
        pauta = PautaBuilder.umaPautaQualquer().build();
    }

    @Test
    public void aoChamarMetodoGetDataCriacaoDadoQueExistamTresAssembleiasOrdenadasDeveriaRetonarAhDataCriacaoDaAssembleiaMaisRecente() {
        pauta.setAssembleias(gerarTresAssembleiasOrdenadas());
        assertEquals(assembleiaAtual.getDataCriacao(), pauta.getDataCriacao());
    }

    @Test
    public void aoChamarMetodoGetStatusAssembleiaDadoQueExistamTresAssembleiasOrdenadasDeveriaRetonarOhStatusDaAssembleiaMaisRecente() {
        pauta.setAssembleias(gerarTresAssembleiasOrdenadas());
        assertEquals(assembleiaAtual.obterStatusAssembleia(), pauta.getStatusAssembleia());
    }

    @Test
    public void aoChamarMetodoGetQuantidadeVotosSimDadoQueExistamTresAssembleiasOrdenadasDeveriaRetonarAhQuantidadeVotosSimDaAssembleiaMaisRecente() {
        pauta.setAssembleias(gerarTresAssembleiasOrdenadas());
        assertEquals(assembleiaAtual.obterQuantidadeVotosSim(), pauta.getQuantidadeVotosSim());
    }

    @Test
    public void aoChamarMetodoGetQuantidadeVotosNaoDadoQueExistamTresAssembleiasOrdenadasDeveriaRetonarAhQuantidadeVotosNaoDaAssembleiaMaisRecente() {
        pauta.setAssembleias(gerarTresAssembleiasOrdenadas());
        assertEquals(assembleiaAtual.obterQuantidadeVotosNao(), pauta.getQuantidadeVotosNao());
    }

    private List<Assembleia> gerarTresAssembleiasOrdenadas() {
        Assembleia assembleiaCinquentaMinutosAtras = gerarAssembleia(50, 5, 5);
        Assembleia assembleiaSeisMinutosAtras = gerarAssembleia(6, 3, 7);
        assembleiaAtual = gerarAssembleia(0, 7, 3);
        return Arrays.asList(assembleiaCinquentaMinutosAtras, assembleiaSeisMinutosAtras, assembleiaAtual);
    }

    private Assembleia gerarAssembleia(int minutos, int quantidadeVotosSim, int quantidadeVotosNao) {
        return AssembleiaBuilder.umaAssembleiaQualquer()
                .comId(Long.valueOf(minutos))
                .comDataCriacao(DataHoraBuilder.umaData().nMinutosAtras(minutos).build())
                .comTempoDuracao(5)
                .comVotos(gerarVotos(quantidadeVotosSim, quantidadeVotosNao))
                .build();
    }

    private List<Voto> gerarVotos(int quantidadeVotosSim, int quantidadeVotosNao) {
        List<Voto> votos = new ArrayList<>();
        for (int i = 0; i < quantidadeVotosSim; i++) {
            votos.add(VotoBuilder.umVotoQualquerSim().build());
        }
        for (int i = 0; i < quantidadeVotosNao; i++) {
            votos.add(VotoBuilder.umVotoQualquerNao().build());
        }
        return votos;
    }
}