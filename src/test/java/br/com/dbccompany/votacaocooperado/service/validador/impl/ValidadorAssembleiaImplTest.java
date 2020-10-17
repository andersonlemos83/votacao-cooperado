package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.DataHoraBuilder;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.repository.PautaRepository;
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

import java.util.Date;
import java.util.Optional;

import static br.com.dbccompany.votacaocooperado.builder.PautaBuilder.umaPautaQualquer;
import static java.util.Optional.ofNullable;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorAssembleiaImplTest {

    private ValidadorAssembleia validadorAssembleia;

    @Mock
    private AssembleiaRepository assembleiaRepositoryMock;

    @Mock
    private PautaRepository pautaRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long idAssembleia;
    private Assembleia assembleia;
    private Optional<Assembleia> assembleiaOptional;

    @Before
    public void inicializarContexto() {
        validadorAssembleia = new ValidadorAssembleiaImpl(assembleiaRepositoryMock, pautaRepositoryMock);

        idAssembleia = 1l;
        assembleia = AssembleiaBuilder.umaAssembleiaQualquer()
                .comId(idAssembleia)
                .comPauta(umaPautaQualquer().build())
                .build();
        assembleiaOptional = ofNullable(assembleia);
    }

    @Test
    public void aoValidarDadoQueNaoExistaAssembleiaDeveriaLancarAhMensagemEsperada() {
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(ofNullable(null));

        exception.expect(NegocioException.class);
        exception.expectMessage("A assembleia informada não existe");

        validadorAssembleia.validar(idAssembleia);
    }

    @Test
    public void aoValidarDadoQueExistaAssembleiaFechadaDeveriaLancarAhMensagemEsperada() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(2);
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(assembleiaOptional);

        exception.expect(NegocioException.class);
        exception.expectMessage("A assembleia informada está fechada");

        validadorAssembleia.validar(idAssembleia);
    }

    @Test
    public void aoValidarDadoQueExistaAssembleiaAbertaNaoDeveriaLancarNenhumaMensagem() {
        assembleia.setDataCriacao(new Date());
        assembleia.setTempoDuracao(10);
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(assembleiaOptional);

        validadorAssembleia.validar(idAssembleia);
    }

    @Test
    public void aoValidarDadoQueNaoExistaPautaDeveriaLancarAhMensagemEsperada() {
        Mockito.when(pautaRepositoryMock.findById(Mockito.any(Long.class))).thenReturn(ofNullable(null));

        exception.expect(NegocioException.class);
        exception.expectMessage("A pauta informada não existe");

        validadorAssembleia.validar(assembleia);
    }

    @Test
    public void aoValidarDadoQueExistaPautaNaoDeveriaLancarNenhumaMensagem() {
        Mockito.when(pautaRepositoryMock.findById(Mockito.any(Long.class))).thenReturn((ofNullable(umaPautaQualquer().build())));

        validadorAssembleia.validar(assembleia);
    }

    private Date obterDataCriacaoExpirada() {
        return DataHoraBuilder.umaData().nMinutosAtras(30).build();
    }
}