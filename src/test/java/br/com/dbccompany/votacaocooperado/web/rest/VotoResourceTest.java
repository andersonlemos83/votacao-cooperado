package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.builder.VotoBuilder;
import br.com.dbccompany.votacaocooperado.builder.VotoDtoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static br.com.dbccompany.votacaocooperado.util.ConstanteUtil.URI_V1_API_VOTOS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private ModelMapper modelMapperMock;

    private VotoDto votoDto;
    private Voto voto;

    @Before
    public void inicializarContexto() {
        votoDto = VotoDtoBuilder.umVotoQualquer().build();
        voto = VotoBuilder.umVotoQualquer().build();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsVotosEsperadas() throws Exception {
        BDDMockito.given(votoServiceMock.listarTodos()).willReturn(Collections.singletonList(voto));
        BDDMockito.given(modelMapperMock.map(voto, VotoDto.class)).willReturn(votoDto);

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].tipoVoto").value(votoDto.getTipoVoto().name()))
                .andExpect(jsonPath("$.[0].idAssociado").value(votoDto.getIdAssociado()))
                .andExpect(jsonPath("$.[0].idAssembleia").value(votoDto.getIdAssembleia()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhVotoEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(VotoDto.class), eq(Voto.class))).willReturn(voto);
        BDDMockito.given(votoServiceMock.cadastrar(Mockito.any(Voto.class))).willReturn(voto);
        BDDMockito.given(modelMapperMock.map(any(Voto.class), eq(VotoDto.class))).willReturn(votoDto);

        ResultActions resultActions = cadastrarVoto();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipoVoto").value(votoDto.getTipoVoto().name()))
                .andExpect(jsonPath("$.idAssociado").value(votoDto.getIdAssociado()))
                .andExpect(jsonPath("$.idAssembleia").value(votoDto.getIdAssembleia()));
    }

    private ResultActions cadastrarVoto() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_V1_API_VOTOS)
                .content(new ObjectMapper().writeValueAsString(votoDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_VOTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}