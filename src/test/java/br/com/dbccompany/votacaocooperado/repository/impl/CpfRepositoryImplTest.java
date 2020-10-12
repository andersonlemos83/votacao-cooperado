package br.com.dbccompany.votacaocooperado.repository.impl;

import br.com.dbccompany.votacaocooperado.repository.CpfRepository;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class CpfRepositoryImplTest {

    private static final String URL_ESPERADA = "https://user-info.herokuapp.com/users/22149030039";

    private CpfRepository cpfRepository;

    @Mock
    private RestTemplate restTemplateMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private String cpf;

    @Before
    public void inicializarContexto() {
        cpfRepository = new CpfRepositoryImpl(restTemplateMock);

        cpf = "22149030039";
    }

    @Test
    public void aoVerificarSeCpfEstaValidoDadoQueCpfEstejaValidoDeveriaRetornarVerdadeiro() {
        Mockito.when(restTemplateMock.getForObject(URL_ESPERADA, Map.class)).thenReturn(new HashMap<String, String>());

        boolean estaValido = cpfRepository.verificarSeEstaValido(cpf);

        assertTrue("Deveria retornar verdadeiro", estaValido);
    }

    @Test
    public void aoVerificarSeEstaValidoDadoQueVotacaoNaoEstejaExpiradaDeveriaRetornarFalso() {
        Mockito.when(restTemplateMock.getForObject(URL_ESPERADA, Map.class)).thenThrow(gerarHttpStatusCodeException());

        boolean estaValido = cpfRepository.verificarSeEstaValido(cpf);

        assertFalse("Deveria retornar falso", estaValido);
    }

    @Test
    public void aoVerificarSeEstaValidoDadoQueServicoEstejaOffilineDeveriaLancarAhMensagemEsperada() {
        Mockito.when(restTemplateMock.getForObject(URL_ESPERADA, Map.class)).thenThrow(new RuntimeException());

        exception.expect(NegocioException.class);
        exception.expectMessage("O serviço de validação do CPF está offiline");

        cpfRepository.verificarSeEstaValido(cpf);
    }

    private HttpStatusCodeException gerarHttpStatusCodeException() {
        return new HttpStatusCodeException(HttpStatus.NO_CONTENT) {
        };
    }
}