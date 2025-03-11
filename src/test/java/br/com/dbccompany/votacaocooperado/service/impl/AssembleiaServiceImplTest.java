package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
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
public class AssembleiaServiceImplTest {

    private AssembleiaService assembleiaService;

    @Mock
    private ValidadorAssembleia validadorAssembleiaMock;

    @Mock
    private AssembleiaRepository assembleiaRepositoryMock;

    private List<Assembleia> assembleiasEsperadas;
    private Assembleia assembleiaEsperada;

    @BeforeEach
    public void inicializarContexto() {
        assembleiaService = new AssembleiaServiceImpl(validadorAssembleiaMock, assembleiaRepositoryMock);

        assembleiasEsperadas = new ArrayList<>();
        assembleiaEsperada = AssembleiaBuilder.umaAssembleiaQualquer().build();
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

        verify(validadorAssembleiaMock).validar(assembleiaEsperada);
        verify(assembleiaRepositoryMock).save(assembleiaEsperada);
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhAssembleiaEsperada() {
        Mockito.when(assembleiaRepositoryMock.save(assembleiaEsperada)).thenReturn(assembleiaEsperada);

        Assembleia assembleiaRetornada = assembleiaService.cadastrar(assembleiaEsperada);

        assertSame(assembleiaEsperada, assembleiaRetornada);
    }
}