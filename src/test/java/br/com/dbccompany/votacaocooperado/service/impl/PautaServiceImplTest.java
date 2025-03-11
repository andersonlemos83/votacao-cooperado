package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.builder.PautaBuilder;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.repository.PautaRepository;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class PautaServiceImplTest {

    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepositoryMock;

    private List<Pauta> pautasEsperadas;
    private Pauta pautaEsperada;
    private Long idEsperado;
    private Optional<Pauta> pautaOptionalEsperada;

    @BeforeEach
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

        NegocioException thrown = assertThrows(NegocioException.class, () -> pautaService.buscarPorId(idEsperado));
        assertEquals("A pauta informada não exite", thrown.getMessage());
    }
}