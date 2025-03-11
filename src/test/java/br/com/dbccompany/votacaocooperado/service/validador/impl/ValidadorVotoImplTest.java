package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.builder.VotoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        associado = AssociadoBuilder.umAssociadoQualquer().build();
        assembleia = AssembleiaBuilder.umaAssembleiaQualquer().build();
        voto = VotoBuilder.umVotoQualquer().comAssociado(associado).comAssembleia(assembleia).build();
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

        validadorVoto.validar(voto);
    }
}
