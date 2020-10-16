package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.AssembleiaDtoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoCooperadoApplication.class)
@AutoConfigureMockMvc
public class AssembleiaResourceTest {

    private static final String API_ASSEMBLEIA = "/api/assembleia";

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private AssembleiaService assembleiaServiceMock;

    @MockBean
    private ModelMapper modelMapperMock;

    private AssembleiaDto assembleiaDto;
    private Assembleia assembleia;
    private Date dataCriacao;

    @Before
    public void inicializarContexto() {
        dataCriacao = new Date();
        assembleiaDto = AssembleiaDtoBuilder.umaAssembleiaQualquer().comDataCriacao(dataCriacao).build();
        assembleia = AssembleiaBuilder.umaAssembleiaQualquer().comDataCriacao(dataCriacao).build();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsAssembleiasEsperadas() throws Exception {
        BDDMockito.given(assembleiaServiceMock.listarTodos()).willReturn(Arrays.asList(assembleia));
        BDDMockito.given(modelMapperMock.map(assembleia, AssembleiaDto.class)).willReturn(assembleiaDto);

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].dataCriacao").value(converter(assembleiaDto.getDataCriacao())))
                .andExpect(jsonPath("$.[0].tempoDuracao").value(assembleiaDto.getTempoDuracao()))
                .andExpect(jsonPath("$.[0].idPauta").value(assembleiaDto.getIdPauta()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhAssembleiaEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(AssembleiaDto.class), eq(Assembleia.class))).willReturn(assembleia);
        BDDMockito.given(assembleiaServiceMock.cadastrar(Mockito.any(Assembleia.class))).willReturn(assembleia);
        BDDMockito.given(modelMapperMock.map(any(Assembleia.class), eq(AssembleiaDto.class))).willReturn(assembleiaDto);

        ResultActions resultActions = cadastrarAssembleia();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dataCriacao").value(converter(assembleiaDto.getDataCriacao())))
                .andExpect(jsonPath("$.tempoDuracao").value(assembleiaDto.getTempoDuracao()))
                .andExpect(jsonPath("$.idPauta").value(assembleiaDto.getIdPauta()));
    }

    private ResultActions cadastrarAssembleia() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(API_ASSEMBLEIA)
                .content(new ObjectMapper().writeValueAsString(assembleiaDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(API_ASSEMBLEIA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private String converter(Date data) {
        return MessageFormat.format("{0,date,dd/MM/yyyy HH:mm:ss}", data);
    }
}
