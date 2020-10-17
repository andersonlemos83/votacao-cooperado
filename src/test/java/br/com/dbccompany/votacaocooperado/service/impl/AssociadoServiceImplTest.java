package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.AssociadoRepository;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoServiceImplTest {

    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepositoryMock;

    @Mock
    private ValidadorAssociado validadorAssociadoMock;

    private List<Associado> associadosEsperados;
    private Associado associadoEsperado;

    @Before
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