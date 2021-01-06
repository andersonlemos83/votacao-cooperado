package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.builder.PautaBuilder;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.repository.PautaRepository;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceImplTest {

    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private List<Pauta> pautasEsperadas;
    private Pauta pautaEsperada;
    private Long idEsperado;
    private Optional<Pauta> pautaOptionalEsperada;

    @Before
    public void inicializarContexto() {
        pautaService = new PautaServiceImpl(pautaRepositoryMock);

        pautasEsperadas = new ArrayList<>();
        pautaEsperada = PautaBuilder.umaPautaQualquer().build();
        idEsperado = 1L;
        pautaOptionalEsperada = ofNullable(pautaEsperada);
    }

    @Test
    public void aolistarTodosDeveriaRetonarAsPautasEsperadas() {
        Mockito.when(pautaRepositoryMock.findAll()).thenReturn(pautasEsperadas);

        List<Pauta> pautasRetonadas = pautaService.listarTodos();

        assertSame(pautasEsperadas, pautasRetonadas);
    }

    @Test
    public void aoCadastrarDeveriaRealizarCadastroDaPautaEsperada() {
        Mockito.when(pautaRepositoryMock.save(pautaEsperada)).thenReturn(pautaEsperada);

        pautaService.cadastrar(pautaEsperada);

        verify(pautaRepositoryMock).save(pautaEsperada);
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhPautaEsperada() {
        Mockito.when(pautaRepositoryMock.save(pautaEsperada)).thenReturn(pautaEsperada);

        Pauta pautaRetornada = pautaService.cadastrar(pautaEsperada);

        assertSame(pautaEsperada, pautaRetornada);
    }

    @Test
    public void aoBuscarPorIdDadoQueExistaPautaDeveriaRetonarAhPautaEsperada() {
        Mockito.when(pautaRepositoryMock.findById(idEsperado)).thenReturn(pautaOptionalEsperada);

        Pauta pautaRetornada = pautaService.buscarPorId(idEsperado);

        assertSame(pautaEsperada, pautaRetornada);
    }

    @Test
    public void aoBuscarPorIdDadoQueNaoExistaPautaDeveriaLancarAhMensagemEsperada() {
        Mockito.when(pautaRepositoryMock.findById(idEsperado)).thenReturn(Optional.empty());

        exception.expect(NegocioException.class);
        exception.expectMessage("A pauta informada não exite");

        pautaService.buscarPorId(idEsperado);
    }
}
