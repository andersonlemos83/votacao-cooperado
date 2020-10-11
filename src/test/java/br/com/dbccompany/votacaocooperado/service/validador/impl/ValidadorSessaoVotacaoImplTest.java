package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.repository.SessaoVotacaoRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorSessaoVotacao;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static java.util.Calendar.MINUTE;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorSessaoVotacaoImplTest {

    private ValidadorSessaoVotacao validadorSessaoVotacao;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long idSessaoVotacao;
    private SessaoVotacao sessaoVotacao;
    private Optional<SessaoVotacao> sessaoVotacaoOptional;

    @Before
    public void inicializarContexto() {
        validadorSessaoVotacao = new ValidadorSessaoVotacaoImpl(sessaoVotacaoRepositoryMock);

        idSessaoVotacao = 1l;
        sessaoVotacao = new SessaoVotacao();
        sessaoVotacaoOptional = Optional.ofNullable(sessaoVotacao);
    }

    @Test
    public void aoValidarDadoQueNaoExistaSessaoVotacaoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(sessaoVotacaoRepositoryMock.findById(idSessaoVotacao)).thenReturn(Optional.ofNullable(null));

        exception.expect(NegocioException.class);
        exception.expectMessage("A sessão de votação informada não existe");

        validadorSessaoVotacao.validar(idSessaoVotacao);
    }

    @Test
    public void aoValidarDadoQueExistaSessaoVotacaoFechadaDeveriaLancarAhMensagemEsperada() {
        sessaoVotacao.setDataCriacao(obterDataCriacaoExpirada());
        sessaoVotacao.setTempoDuracao(2);
        Mockito.when(sessaoVotacaoRepositoryMock.findById(idSessaoVotacao)).thenReturn(sessaoVotacaoOptional);

        exception.expect(NegocioException.class);
        exception.expectMessage("A sessão de votação informada está fechada");

        validadorSessaoVotacao.validar(idSessaoVotacao);
    }

    @Test
    public void aoValidarDadoQueExistaSessaoVotacaoAbertaNaoDeveriaLancarNenhumaMensagem() {
        sessaoVotacao.setDataCriacao(new Date());
        sessaoVotacao.setTempoDuracao(10);
        Mockito.when(sessaoVotacaoRepositoryMock.findById(idSessaoVotacao)).thenReturn(sessaoVotacaoOptional);

        validadorSessaoVotacao.validar(idSessaoVotacao);
    }

    private Date obterDataCriacaoExpirada() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(MINUTE, -30);
        return calendar.getTime();
    }
}
