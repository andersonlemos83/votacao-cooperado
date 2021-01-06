package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.builder.AssociadoDtoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
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

import static br.com.dbccompany.votacaocooperado.util.ConstanteUtil.URI_V1_API_ASSOCIADOS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoCooperadoApplication.class)
@AutoConfigureMockMvc
public class AssociadoResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private AssociadoService associadoServiceMock;

    @MockBean
    private ModelMapper modelMapperMock;

    private AssociadoDto associadoDto;
    private Associado associado;

    @Before
    public void inicializarContexto() {
        associadoDto = AssociadoDtoBuilder.umAssociadoQualquer().build();
        associado = AssociadoBuilder.umAssociadoQualquer().build();
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
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ASSOCIADOS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}