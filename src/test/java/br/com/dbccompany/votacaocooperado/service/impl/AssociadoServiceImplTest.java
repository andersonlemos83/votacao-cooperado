package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.AssociadoRepository;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class AssociadoServiceImplTest {

    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepositoryMock;

    @Mock
    private ValidadorAssociado validadorAssociadoMock;

    private List<Associado> associadosEsperados;
    private Associado associadoEsperado;

    @BeforeEach
    public void inicializarContexto() {
        associadoService = new AssociadoServiceImpl(validadorAssociadoMock, associadoRepositoryMock);

        associadosEsperados = new ArrayList<>();
        associadoEsperado = AssociadoBuilder.umAssociadoQualquer().build();
    }

    @Test
    public void aolistarTodosDeveriaRetonarOsAssociadosEsperados() {
        Mockito.when(associadoRepositoryMock.findAll()).thenReturn(associadosEsperados);

        List<Associado> associadosRetonadas = associadoService.listarTodos();

        assertSame(associadosEsperados, associadosRetonadas);
    }

    @Test
    public void aoCadastrarDeveriaRealizarValidacoesIhCadastroDoAssociadoEsperado() {
        Mockito.when(associadoRepositoryMock.save(associadoEsperado)).thenReturn(associadoEsperado);

        associadoService.cadastrar(associadoEsperado);

        verify(validadorAssociadoMock).validar(associadoEsperado);
        verify(associadoRepositoryMock).save(associadoEsperado);
    }

    @Test
    public void aoCadastrarDeveriaRetornarOhAssociadoEsperado() {
        Mockito.when(associadoRepositoryMock.save(associadoEsperado)).thenReturn(associadoEsperado);

        Associado associadoRetornado = associadoService.cadastrar(associadoEsperado);

        assertSame(associadoEsperado, associadoRetornado);
    }
}