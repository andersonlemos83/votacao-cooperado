package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.client.UsuarioClient;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.AssociadoRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ValidadorAssociadoImplTest {

    private ValidadorAssociado validadorAssociado;

    @Mock
    private UsuarioClient usuarioClientMock;

    @Mock
    private AssociadoRepository associadoRepositoryMock;

    private Associado associado;

    @BeforeEach
    public void inicializarContexto() {
        validadorAssociado = new ValidadorAssociadoImpl(usuarioClientMock, associadoRepositoryMock);

        associado = Instancio.create(Associado.class);
    }

    @Test
    public void aoValidarDadoQueCpfSejaInvalidoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(usuarioClientMock.verificarSeEstaValido(associado.getCpf())).thenReturn(false);

        NegocioException thrown = assertThrows(NegocioException.class, () -> validadorAssociado.validar(associado));
        assertEquals("O CPF do associado é inválido", thrown.getMessage());
    }

    @Test
    public void aoValidarDadoQueCpfSejaValidoIhJaEstejaCadastradoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(usuarioClientMock.verificarSeEstaValido(associado.getCpf())).thenReturn(true);
        Mockito.when(associadoRepositoryMock.findByCpf(associado.getCpf())).thenReturn(ofNullable(associado));

        NegocioException thrown = assertThrows(NegocioException.class, () -> validadorAssociado.validar(associado));
        assertEquals("O CPF informado já está cadastrado", thrown.getMessage());
    }

    @Test
    public void aoValidarDadoQueCpfSejaValidoIhNaoEstejaCadastradoNaoDeveriaLancarNenhumaMensagem() {
        Mockito.when(usuarioClientMock.verificarSeEstaValido(associado.getCpf())).thenReturn(true);
        validadorAssociado.validar(associado);
    }
}