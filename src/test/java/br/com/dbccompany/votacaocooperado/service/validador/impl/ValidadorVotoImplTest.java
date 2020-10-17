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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorVotoImplTest {

    private ValidadorVoto validadorVoto;

    @Mock
    private VotoRepository votoRepositoryMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Voto voto;
    private Assembleia assembleia;
    private Associado associado;

    @Before
    public void inicializarContexto() {
        validadorVoto = new ValidadorVotoImpl(votoRepositoryMock);

        associado = AssociadoBuilder.umAssociadoQualquer().build();
        assembleia = AssembleiaBuilder.umaAssembleiaQualquer().build();
        voto = VotoBuilder.umVotoQualquer().comAssociado(associado).comAssembleia(assembleia).build();
    }

    @Test
    public void aoValidarDadoQueExistaVotoDeveriaLancarAhMensagemEsperada() {
        Mockito.when(votoRepositoryMock.findByAssociado_IdAndAssembleia_Id(associado.getId(), assembleia.getId())).thenReturn(voto);

        exception.expect(NegocioException.class);
        exception.expectMessage("O associado já exerceu seu direito de voto para esta pauta");

        validadorVoto.validar(voto);
    }

    @Test
    public void aoValidarDadoQueExistaVotoNaoDeveriaLancarNenhumaMensagem() {
        Mockito.when(votoRepositoryMock.findByAssociado_IdAndAssembleia_Id(associado.getId(), assembleia.getId())).thenReturn(null);

        validadorVoto.validar(voto);
    }
}
