package br.com.dbccompany.votacaocooperado.service.validador.impl;

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

        associado = new Associado();
        associado.setId(1l);

        assembleia = new Assembleia();
        assembleia.setId(2l);

        voto = new Voto();
        voto.setAssociado(associado);
        voto.setAssembleia(assembleia);
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
