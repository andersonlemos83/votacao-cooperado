package br.com.dbccompany.votacaocooperado.client.impl;

import br.com.dbccompany.votacaocooperado.client.UsuarioClient;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class UsuarioClientImplTest {

    private static final String URL_ESPERADA = "https://user-info.herokuapp.com/users/22149030039";

    private UsuarioClient usuarioClient;

    @Mock
    private RestTemplate restTemplateMock;

    private String cpf;

    @BeforeEach
    public void inicializarContexto() {
        usuarioClient = new UsuarioClientImpl(restTemplateMock, "https://user-info.herokuapp.com");

        cpf = "22149030039";
    }

    @Test
    public void aoVerificarSeCpfEstaValidoDadoQueCpfEstejaValidoDeveriaRetornarVerdadeiro() {
        Mockito.when(restTemplateMock.getForObject(URL_ESPERADA, String.class)).thenReturn("Fulano");

        boolean estaValido = usuarioClient.verificarSeEstaValido(cpf);

        assertTrue("Deveria retornar verdadeiro", estaValido);
    }

    @Test
    public void aoVerificarSeEstaValidoDadoQueVotacaoNaoEstejaExpiradaDeveriaRetornarFalso() {
        Mockito.when(restTemplateMock.getForObject(URL_ESPERADA, String.class)).thenThrow(gerarBadRequestException());

        boolean estaValido = usuarioClient.verificarSeEstaValido(cpf);

        assertFalse("Deveria retornar falso", estaValido);
    }

    @Test
    public void aoVerificarSeEstaValidoDadoQueServicoEstejaOffilineDeveriaLancarAhMensagemEsperada() {
        Mockito.when(restTemplateMock.getForObject(URL_ESPERADA, String.class)).thenThrow(new RuntimeException());

        NegocioException thrown = assertThrows(NegocioException.class, () -> usuarioClient.verificarSeEstaValido(cpf));
        assertEquals("O serviço de validação do CPF está offline", thrown.getMessage());
    }

    private HttpStatusCodeException gerarBadRequestException() {
        return Instancio.create(HttpClientErrorException.BadRequest.class);
    }
}