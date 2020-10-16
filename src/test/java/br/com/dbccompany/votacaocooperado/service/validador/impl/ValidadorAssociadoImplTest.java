package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.AssociadoRepository;
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

import static java.util.Optional.ofNullable;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorAssociadoImplTest {

    private ValidadorAssociado validadorAssociado;

    @Mock
    private CpfRepository cpfRepositoryMock;

    @Mock
    private AssociadoRepository associadoRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Associado associado;

    @Before
    public void inicializarContexto() {
        validadorAssociado = new ValidadorAssociadoImpl(cpfRepositoryMock, associadoRepositoryMock);

        associado = AssociadoBuilder.umAssociadoQualquer().build();
    }

    @Test
    public void aoValidarDadoQueCpfSejaInvalidoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(cpfRepositoryMock.verificarSeEstaValido(associado.getCpf())).thenReturn(false);

        exception.expect(NegocioException.class);
        exception.expectMessage("O CPF do associado é inválido");

        validadorAssociado.validar(associado);
    }

    @Test
    public void aoValidarDadoQueCpfSejaValidoIhJaEstejaCadastradoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(cpfRepositoryMock.verificarSeEstaValido(associado.getCpf())).thenReturn(true);
        Mockito.when(associadoRepositoryMock.findByCpf(associado.getCpf())).thenReturn(ofNullable(associado));

        exception.expect(NegocioException.class);
        exception.expectMessage("O CPF informado já está cadatrado");

        validadorAssociado.validar(associado);
    }

    @Test
    public void aoValidarDadoQueCpfSejaValidoIhNaoEstejaCadastradoNaoDeveriaLancarNenhumaMensagem() {
        Mockito.when(cpfRepositoryMock.verificarSeEstaValido(associado.getCpf())).thenReturn(true);
        validadorAssociado.validar(associado);
    }
}