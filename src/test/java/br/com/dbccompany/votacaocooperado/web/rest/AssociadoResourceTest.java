package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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

import static br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil.URI_V1_API_ASSOCIADOS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AssociadoResource.class)
public class AssociadoResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockitoBean
    private AssociadoService associadoServiceMock;

    @MockitoBean
    private ModelMapper modelMapperMock;

    private AssociadoDto associadoDto;
    private Associado associado;

    @BeforeEach
    public void inicializarContexto() {
        associadoDto = Instancio.create(AssociadoDto.class);
        associado = Instancio.create(Associado.class);
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsAssociadosEsperadas() throws Exception {
        BDDMockito.given(associadoServiceMock.listarTodos()).willReturn(Collections.singletonList(associado));
        BDDMockito.given(modelMapperMock.map(associado, AssociadoDto.class)).willReturn(associadoDto);

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].nome").value(associadoDto.getNome()))
                .andExpect(jsonPath("$.[0].cpf").value(associadoDto.getCpf()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhAssociadoEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(AssociadoDto.class), eq(Associado.class))).willReturn(associado);
        BDDMockito.given(associadoServiceMock.cadastrar(any(Associado.class))).willReturn(associado);
        BDDMockito.given(modelMapperMock.map(any(Associado.class), eq(AssociadoDto.class))).willReturn(associadoDto);

        ResultActions resultActions = cadastrarAssociado();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(associadoDto.getNome()))
                .andExpect(jsonPath("$.cpf").value(associadoDto.getCpf()));
    }

    private ResultActions cadastrarAssociado() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_V1_API_ASSOCIADOS)
                .content(new ObjectMapper().writeValueAsString(associadoDto))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ASSOCIADOS)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }
}