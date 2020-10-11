package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoCooperadoApplication.class)
@AutoConfigureMockMvc
public class VotoResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private VotoService votoServiceMock;

    @MockBean
    private ConversorVoto conversorVotoMock;

    private VotoDto votoDto;
    private Voto voto;

    @Before
    public void inicializarContexto() {
        votoDto = gerarVotoDto();
        voto = gerarVoto();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsVotosEsperadas() throws Exception {
        BDDMockito.given(votoServiceMock.listarTodos()).willReturn(Arrays.asList(voto));
        BDDMockito.given(conversorVotoMock.converter(Mockito.any(List.class))).willReturn(Arrays.asList(votoDto));

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].tipoVoto").value(votoDto.getTipoVoto().name()))
                .andExpect(jsonPath("$.[0].idAssociado").value(votoDto.getIdAssociado()))
                .andExpect(jsonPath("$.[0].idAssembleia").value(votoDto.getIdAssembleia()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhVotoEsperada() throws Exception {
        BDDMockito.given(conversorVotoMock.converter(Mockito.any(VotoDto.class))).willReturn(voto);
        BDDMockito.given(votoServiceMock.cadastrar(Mockito.any(Voto.class))).willReturn(voto);
        BDDMockito.given(conversorVotoMock.converter(Mockito.any(Voto.class))).willReturn(votoDto);

        ResultActions resultActions = cadastrarVoto();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipoVoto").value(votoDto.getTipoVoto().name()))
                .andExpect(jsonPath("$.idAssociado").value(votoDto.getIdAssociado()))
                .andExpect(jsonPath("$.idAssembleia").value(votoDto.getIdAssembleia()));
    }

    private ResultActions cadastrarVoto() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/api/voto")
                .content("{\"tipoVoto\":\"SIM\",\"idAssociado\":\"1\",\"idAssembleia\":\"2\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/api/voto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private VotoDto gerarVotoDto() {
        VotoDto votoDto = new VotoDto();
        votoDto.setTipoVoto(TipoVoto.SIM);
        votoDto.setIdAssociado(1l);
        votoDto.setIdAssembleia(2l);
        return votoDto;
    }

    private Voto gerarVoto() {
        Voto voto = new Voto();
        voto.setTipoVoto(TipoVoto.SIM);
        voto.setAssociado(new Associado(1l));
        voto.setAssembleia(new Assembleia(2l));
        return voto;
    }
}