package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.VotoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
import org.junit.Before;
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
public class VotoServiceImplTest {

    private VotoService votoService;

    @Mock
    private VotoRepository votoRepositoryMock;

    @Mock
    private ValidadorAssembleia validadorAssembleiaMock;

    @Mock
    private ValidadorVoto validadorVotoMock;

    private List<Voto> votosEsperados;
    private Voto votoEsperado;
    private Long idAssembleiEsperado;

    @Before
    public void inicializarContexto() {
        votoService = new VotoServiceImpl(votoRepositoryMock, validadorAssembleiaMock, validadorVotoMock);

        votosEsperados = new ArrayList<>();

        idAssembleiEsperado = 1L;

        Assembleia assembleia = AssembleiaBuilder.umaAssembleia().comId(idAssembleiEsperado).build();
        votoEsperado = VotoBuilder.umVotoQualquer().comAssembleia(assembleia).build();
    }

    @Test
    public void aolistarTodosDeveriaRetonarOsVotosEsperados() {
        Mockito.when(votoRepositoryMock.findAll()).thenReturn(votosEsperados);

        List<Voto> votosRetonados = votoService.listarTodos();

        assertSame(votosEsperados, votosRetonados);
    }

    @Test
    public void aoCadastrarDeveriaRealizarValidacoesIhCadastroDoVotoEsperado() {
        Mockito.when(votoRepositoryMock.save(votoEsperado)).thenReturn(votoEsperado);

        votoService.cadastrar(votoEsperado);

        verify(validadorAssembleiaMock).validar(idAssembleiEsperado);
        verify(validadorVotoMock).validar(votoEsperado);
        verify(votoRepositoryMock).save(votoEsperado);
    }

    @Test
    public void aoCadastrarDeveriaRetornarOhVotoEsperado() {
        Mockito.when(votoRepositoryMock.save(votoEsperado)).thenReturn(votoEsperado);

        Voto votoRetornado = votoService.cadastrar(votoEsperado);

        assertSame(votoEsperado, votoRetornado);
    }
}