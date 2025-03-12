package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ValidadorVotoImplTest {

    private ValidadorVoto validadorVoto;

    @Mock
    private VotoRepository votoRepositoryMock;

    private Voto voto;
    private Assembleia assembleia;
    private Associado associado;

    @BeforeEach
    public void inicializarContexto() {
        validadorVoto = new ValidadorVotoImpl(votoRepositoryMock);

        associado = Instancio.create(Associado.class);
        assembleia = Instancio.create(Assembleia.class);
        voto = Instancio.of(Voto.class)
                .set(Select.field("associado"), associado)
                .set(Select.field("assembleia"), assembleia)
                .create();
    }

    @Test
    public void aoValidarDadoQueExistaVotoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(votoRepositoryMock.findByAssociado_IdAndAssembleia_Id(associado.getId(), assembleia.getId())).thenReturn(voto);

        NegocioException thrown = assertThrows(NegocioException.class, () -> validadorVoto.validar(voto));
        assertEquals("O associado já exerceu seu direito de voto para esta pauta", thrown.getMessage());
    }

    @Test
    public void aoValidarDadoQueExistaVotoNaoDeveriaLancarNenhumaMensagem() {
        Mockito.when(votoRepositoryMock.findByAssociado_IdAndAssembleia_Id(associado.getId(), assembleia.getId())).thenReturn(null);

        assertDoesNotThrow(() -> validadorVoto.validar(voto));
    }
}
