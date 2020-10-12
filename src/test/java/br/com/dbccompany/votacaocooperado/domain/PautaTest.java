package br.com.dbccompany.votacaocooperado.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PautaTest {

    private Pauta pauta;
    private Assembleia assembleiaComDataMaisRecente;

    @Before
    public void inicializarContexto() {
        pauta = new Pauta();
    }

    @Test
    public void aoObterUltimaAssembleiaDadoQueExistamTresAssembleiasOrdenadasDeveriaRetonarAhUltimaAssembleia() {
        pauta.setAssembleias(gerarTresAssembleiasOrdenadas());

        final Optional<Assembleia> assembleiaOptional = pauta.obterUltimaAssembleia();

        assertEquals(assembleiaComDataMaisRecente, assembleiaOptional.get());
    }

    private List<Assembleia> gerarTresAssembleiasOrdenadas() {
        assembleiaComDataMaisRecente = gerarAssembleia(1);
        return Arrays.asList(gerarAssembleia(21), gerarAssembleia(11), assembleiaComDataMaisRecente);
    }

    private Assembleia gerarAssembleia(int minutos) {
        Calendar dataCriacaoCalendar = Calendar.getInstance();
        dataCriacaoCalendar.add(Calendar.MINUTE, -minutos);

        Assembleia assembleia = new Assembleia();
        assembleia.setId(Long.valueOf(minutos));
        assembleia.setDataCriacao(dataCriacaoCalendar.getTime());
        return assembleia;
    }
}