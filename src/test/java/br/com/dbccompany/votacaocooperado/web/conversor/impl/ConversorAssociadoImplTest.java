package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssociado;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class ConversorAssociadoImplTest {

    private ConversorAssociado conversorAssociado;

    private Associado associado;
    private AssociadoDto associadoDto;

    @Before
    public void inicializarContexto() {
        conversorAssociado = new ConversorAssociadoImpl();

        associado = gerarAssociado();
        associadoDto = gerarAssociadoDto();
    }

    @Test
    public void aoConverterParaEntidadeDeveriaRetonarAhEntidadeEsperada() {
        Associado associadoRetornada = conversorAssociado.converter(associadoDto);

        assertEquals(associado.getId(), associadoRetornada.getId());
        assertEquals(associado.getNome(), associadoRetornada.getNome());
        assertEquals(associado.getCpf(), associadoRetornada.getCpf());
    }

    @Test
    public void aoConverterParaListaDtoDeveriaRetonarAhListaDtoEsperada() {
        List<AssociadoDto> associadosDto = conversorAssociado.converter(Arrays.asList(associado));

        assertFalse(associadosDto.isEmpty());

        AssociadoDto associadoDtoRetornado = associadosDto.get(0);
        assertEquals(associadoDto.getId(), associadoDtoRetornado.getId());
        assertEquals(associadoDto.getNome(), associadoDtoRetornado.getNome());
        assertEquals(associadoDto.getCpf(), associadoDtoRetornado.getCpf());
    }

    private Associado gerarAssociado() {
        Associado associado = new Associado();
        associado.setId(1l);
        associado.setNome("Anderson");
        associado.setCpf("05551876044");
        return associado;
    }

    private AssociadoDto gerarAssociadoDto() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setId(1l);
        associadoDto.setNome("Anderson");
        associadoDto.setCpf("05551876044");
        return associadoDto;
    }
}
