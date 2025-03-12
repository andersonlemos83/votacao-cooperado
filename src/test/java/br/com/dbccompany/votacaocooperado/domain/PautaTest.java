package br.com.dbccompany.votacaocooperado.domain;

import br.com.dbccompany.votacaocooperado.helper.builder.DataHoraBuilder;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.NAO;
import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.SIM;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class PautaTest {

    private Pauta pauta;
    private Assembleia assembleiaAtual;

    @BeforeEach
    public void inicializarContexto() {
        pauta = Instancio.create(Pauta.class);
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
        Assembleia assembleia = Instancio.create(Assembleia.class);
        assembleia.setId((long) minutos);
        assembleia.setDataCriacao(DataHoraBuilder.umaData().nMinutosAtras(minutos).build());
        assembleia.setTempoDuracao(5);
        assembleia.setVotos(gerarVotos(quantidadeVotosSim, quantidadeVotosNao));
        return assembleia;
    }

    private List<Voto> gerarVotos(int quantidadeVotosSim, int quantidadeVotosNao) {
        List<Voto> votos = new ArrayList<>();
        for (int i = 0; i < quantidadeVotosSim; i++) {
            Voto voto = Instancio.of(Voto.class).set(Select.field("tipoVoto"), SIM).create();
            votos.add(voto);
        }
        for (int i = 0; i < quantidadeVotosNao; i++) {
            Voto voto = Instancio.of(Voto.class).set(Select.field("tipoVoto"), NAO).create();
            votos.add(voto);
        }
        return votos;
    }
}