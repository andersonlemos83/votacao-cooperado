package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.builder.AssembleiaDtoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;

import static br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil.URI_V1_API_ASSEMBLEIAS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AssembleiaResource.class)
public class AssembleiaResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockitoBean
    private AssembleiaService assembleiaServiceMock;

    @MockitoBean
    private ModelMapper modelMapperMock;

    private AssembleiaDto assembleiaDto;
    private Assembleia assembleia;
    private Date dataCriacao;

    @BeforeEach
    public void inicializarContexto() {
        dataCriacao = new Date();
        assembleiaDto = AssembleiaDtoBuilder.umaAssembleiaQualquer().comDataCriacao(dataCriacao).build();
        assembleia = AssembleiaBuilder.umaAssembleiaQualquer().comDataCriacao(dataCriacao).build();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsAssembleiasEsperadas() throws Exception {
        BDDMockito.given(assembleiaServiceMock.listarTodos()).willReturn(Collections.singletonList(assembleia));
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
        return mvc.perform(MockMvcRequestBuilders.post(URI_V1_API_ASSEMBLEIAS)
                .content(new ObjectMapper().writeValueAsString(assembleiaDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ASSEMBLEIAS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private String converter(Date data) {
        return MessageFormat.format("{0,date,dd/MM/yyyy HH:mm:ss}", data);
    }
}
