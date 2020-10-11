package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorSessaoVotacao;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
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
public class VotoServiceImplTest {

    private VotoService votoService;

    @Mock
    private VotoRepository votoRepositoryMock;

    @Mock
    private ValidadorSessaoVotacao validadorSessaoVotacaoMock;

    @Mock
    private ValidadorVoto validadorVotoMock;

    private List<Voto> votosEsperados;
    private Voto votoEsperado;
    private Long idSessaoVotacaoEsperado;

    @Before
    public void inicializarContexto() {
        votoService = new VotoServiceImpl(votoRepositoryMock, validadorSessaoVotacaoMock, validadorVotoMock);

        votosEsperados = new ArrayList<>();

        idSessaoVotacaoEsperado = 1l;

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setId(idSessaoVotacaoEsperado);

        votoEsperado = new Voto();
        votoEsperado.setSessaoVotacao(sessaoVotacao);
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

        verify(validadorSessaoVotacaoMock).validar(idSessaoVotacaoEsperado);
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
