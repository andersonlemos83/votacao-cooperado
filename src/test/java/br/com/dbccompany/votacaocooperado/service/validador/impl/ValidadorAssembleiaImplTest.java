package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
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
public class ValidadorAssembleiaImplTest {

    private ValidadorAssembleia validadorAssembleia;

    @Mock
    private AssembleiaRepository assembleiaRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long idAssembleia;
    private Assembleia assembleia;
    private Optional<Assembleia> assembleiaOptional;

    @Before
    public void inicializarContexto() {
        validadorAssembleia = new ValidadorAssembleiaImpl(assembleiaRepositoryMock);

        idAssembleia = 1l;
        assembleia = new Assembleia();
        assembleiaOptional = Optional.ofNullable(assembleia);
    }

    @Test
    public void aoValidarDadoQueNaoExistaAssembleiaDeveriaLancarAhMensagemEsperada() {
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(Optional.ofNullable(null));

        exception.expect(NegocioException.class);
        exception.expectMessage("A assembleia de votação informada não existe");

        validadorAssembleia.validar(idAssembleia);
    }

    @Test
    public void aoValidarDadoQueExistaAssembleiaFechadaDeveriaLancarAhMensagemEsperada() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(2);
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(assembleiaOptional);

        exception.expect(NegocioException.class);
        exception.expectMessage("A assembleia de votação informada está fechada");

        validadorAssembleia.validar(idAssembleia);
    }

    @Test
    public void aoValidarDadoQueExistaAssembleiaAbertaNaoDeveriaLancarNenhumaMensagem() {
        assembleia.setDataCriacao(new Date());
        assembleia.setTempoDuracao(10);
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(assembleiaOptional);

        validadorAssembleia.validar(idAssembleia);
    }

    private Date obterDataCriacaoExpirada() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(MINUTE, -30);
        return calendar.getTime();
    }
}
