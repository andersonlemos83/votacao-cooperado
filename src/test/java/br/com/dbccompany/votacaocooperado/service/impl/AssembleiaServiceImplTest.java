package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
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
public class AssembleiaServiceImplTest {

    private AssembleiaService assembleiaService;

    @Mock
    private AssembleiaRepository assembleiaRepositoryMock;

    private List<Assembleia> assembleiasEsperadas;
    private Assembleia assembleiaEsperada;

    @Before
    public void inicializarContexto() {
        assembleiaService = new AssembleiaServiceImpl(assembleiaRepositoryMock);

        assembleiasEsperadas = new ArrayList<>();
        assembleiaEsperada = new Assembleia();
    }

    @Test
    public void aolistarTodosDeveriaRetonarAsAssembleiasEsperadas() {
        Mockito.when(assembleiaRepositoryMock.findAll()).thenReturn(assembleiasEsperadas);

        List<Assembleia> assembleiasEsperadasRetonadas = assembleiaService.listarTodos();

        assertSame(assembleiasEsperadas, assembleiasEsperadasRetonadas);
    }

    @Test
    public void aoCadastrarDeveriaRealizarCadastroDaAssembleiasEsperada() {
        Mockito.when(assembleiaRepositoryMock.save(assembleiaEsperada)).thenReturn(assembleiaEsperada);

        assembleiaService.cadastrar(assembleiaEsperada);

        verify(assembleiaRepositoryMock).save(assembleiaEsperada);
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhAssembleiaEsperada() {
        Mockito.when(assembleiaRepositoryMock.save(assembleiaEsperada)).thenReturn(assembleiaEsperada);

        Assembleia assembleiaRetornada = assembleiaService.cadastrar(assembleiaEsperada);

        assertSame(assembleiaEsperada, assembleiaRetornada);
    }
}