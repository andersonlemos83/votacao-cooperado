package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.helper.builder.DataHoraBuilder;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.repository.PautaRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ValidadorAssembleiaImplTest {

    private ValidadorAssembleia validadorAssembleia;

    @Mock
    private AssembleiaRepository assembleiaRepositoryMock;

    @Mock
    private PautaRepository pautaRepositoryMock;

    private Long idAssembleia;
    private Assembleia assembleia;
    private Optional<Assembleia> assembleiaOptional;

    @BeforeEach
    public void inicializarContexto() {
        validadorAssembleia = new ValidadorAssembleiaImpl(assembleiaRepositoryMock, pautaRepositoryMock);

        idAssembleia = 1L;
        assembleia = Instancio.of(Assembleia.class)
                .set(Select.field("id"), idAssembleia)
                .set(Select.field("pauta"), Instancio.create(Pauta.class))
                .create();
        assembleiaOptional = ofNullable(assembleia);
    }

    @Test
    public void aoValidarDadoQueNaoExistaAssembleiaDeveriaLancarAhMensagemEsperada() {
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(Optional.empty());

        NegocioException thrown = assertThrows(NegocioException.class, () -> validadorAssembleia.validar(idAssembleia));
        assertEquals("A assembleia informada não existe", thrown.getMessage());
    }

    @Test
    public void aoValidarDadoQueExistaAssembleiaFechadaDeveriaLancarAhMensagemEsperada() {
        assembleia.setDataCriacao(obterDataCriacaoExpirada());
        assembleia.setTempoDuracao(2);
        Mockito.when(assembleiaRepositoryMock.findById(idAssembleia)).thenReturn(assembleiaOptional);

        NegocioException thrown = assertThrows(NegocioException.class, () -> validadorAssembleia.validar(idAssembleia));
        assertEquals("A assembleia informada está fechada", thrown.getMessage());
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
        Mockito.when(pautaRepositoryMock.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        NegocioException thrown = assertThrows(NegocioException.class, () -> validadorAssembleia.validar(assembleia));
        assertEquals("A pauta informada não existe", thrown.getMessage());
    }

    @Test
    public void aoValidarDadoQueExistaPautaNaoDeveriaLancarNenhumaMensagem() {
        Pauta pauta = Instancio.create(Pauta.class);
        Mockito.when(pautaRepositoryMock.findById(Mockito.any(Long.class))).thenReturn((ofNullable(pauta)));

        validadorAssembleia.validar(assembleia);
    }

    private Date obterDataCriacaoExpirada() {
        return DataHoraBuilder.umaData().nMinutosAtras(30).build();
    }
}