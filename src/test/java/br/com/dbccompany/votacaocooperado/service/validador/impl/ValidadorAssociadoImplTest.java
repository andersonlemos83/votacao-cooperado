package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.CpfRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorAssociadoImplTest {

    private ValidadorAssociado validadorAssociado;

    @Mock
    private CpfRepository cpfRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Associado associado;

    @Before
    public void inicializarContexto() {
        validadorAssociado = new ValidadorAssociadoImpl(cpfRepositoryMock);

        associado = new Associado();
        associado.setCpf("36288153044");
    }

    @Test
    public void aoValidarDadoQueCpfSejaInvalidoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(cpfRepositoryMock.verificarSeEstaValido(associado.getCpf())).thenReturn(false);

        exception.expect(NegocioException.class);
        exception.expectMessage("O CPF do associado é inválido");

        validadorAssociado.validar(associado);
    }

    @Test
    public void aoValidarDadoQueCpfSejaValidoNaoDeveriaLancarNenhumaMensagem() {
        Mockito.when(cpfRepositoryMock.verificarSeEstaValido(associado.getCpf())).thenReturn(true);
        validadorAssociado.validar(associado);
    }
}