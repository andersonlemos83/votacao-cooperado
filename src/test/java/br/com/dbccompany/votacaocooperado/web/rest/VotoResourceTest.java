package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil.URI_V1_API_VOTOS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(VotoResource.class)
public class VotoResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockitoBean
    private VotoService votoServiceMock;

    @MockitoBean
    private ModelMapper modelMapperMock;

    private VotoDto votoDto;
    private Voto voto;

    @BeforeEach
    public void inicializarContexto() {
        votoDto = Instancio.create(VotoDto.class);
        voto = Instancio.create(Voto.class);
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
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_VOTOS)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }
}