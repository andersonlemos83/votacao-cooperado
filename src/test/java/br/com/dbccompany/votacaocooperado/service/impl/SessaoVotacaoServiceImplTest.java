package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.repository.SessaoVotacaoRepository;
import br.com.dbccompany.votacaocooperado.service.SessaoVotacaoService;
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
public class SessaoVotacaoServiceImplTest {

    private SessaoVotacaoService sessaoVotacaoService;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepositoryMock;

    private List<SessaoVotacao> sessoesVotacaoEsperadas;
    private SessaoVotacao sessaoVotacaoEsperada;

    @Before
    public void inicializarContexto() {
        sessaoVotacaoService = new SessaoVotacaoServiceImpl(sessaoVotacaoRepositoryMock);

        sessoesVotacaoEsperadas = new ArrayList<>();
        sessaoVotacaoEsperada = new SessaoVotacao();
    }

    @Test
    public void aolistarTodosDeveriaRetonarAsSessoesVotacaoEsperadas() {
        Mockito.when(sessaoVotacaoRepositoryMock.findAll()).thenReturn(sessoesVotacaoEsperadas);

        List<SessaoVotacao> sessoesVotacaoEsperadasRetonadas = sessaoVotacaoService.listarTodos();

        assertSame(sessoesVotacaoEsperadas, sessoesVotacaoEsperadasRetonadas);
    }

    @Test
    public void aoCadastrarDeveriaRealizarCadastroDaSessaoVotacaoEsperada() {
        Mockito.when(sessaoVotacaoRepositoryMock.save(sessaoVotacaoEsperada)).thenReturn(sessaoVotacaoEsperada);

        sessaoVotacaoService.cadastrar(sessaoVotacaoEsperada);

        verify(sessaoVotacaoRepositoryMock).save(sessaoVotacaoEsperada);
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhSessaoVotacaoEsperada() {
        Mockito.when(sessaoVotacaoRepositoryMock.save(sessaoVotacaoEsperada)).thenReturn(sessaoVotacaoEsperada);

        SessaoVotacao sessaoVotacaoRetornada = sessaoVotacaoService.cadastrar(sessaoVotacaoEsperada);

        assertSame(sessaoVotacaoEsperada, sessaoVotacaoRetornada);
    }
}