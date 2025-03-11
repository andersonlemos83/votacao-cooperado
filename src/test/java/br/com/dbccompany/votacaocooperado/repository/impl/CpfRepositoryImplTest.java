package br.com.dbccompany.votacaocooperado.repository.impl;

import br.com.dbccompany.votacaocooperado.repository.CpfRepository;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class CpfRepositoryImplTest {

    private static final String URL_ESPERADA = "https://user-info.herokuapp.com/users/22149030039";

    private CpfRepository cpfRepository;

    @Mock
    private RestTemplate restTemplateMock;

    private String cpf;

    @BeforeEach
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

        NegocioException thrown = assertThrows(NegocioException.class, () -> cpfRepository.verificarSeEstaValido(cpf));
        assertEquals("O serviço de validação do CPF está offline", thrown.getMessage());
    }

    private HttpStatusCodeException gerarHttpStatusCodeException() {
        return new HttpStatusCodeException(HttpStatus.NO_CONTENT) {
        };
    }
}