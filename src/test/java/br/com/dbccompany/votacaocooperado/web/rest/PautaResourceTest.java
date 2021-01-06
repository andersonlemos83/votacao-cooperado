package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.builder.PautaBuilder;
import br.com.dbccompany.votacaocooperado.builder.PautaConsolidadoDtoBuilder;
import br.com.dbccompany.votacaocooperado.builder.PautaDtoBuilder;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
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

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;

import static br.com.dbccompany.votacaocooperado.util.ConstanteUtil.URI_V1_API_PAUTAS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoCooperadoApplication.class)
@AutoConfigureMockMvc
public class PautaResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private PautaService pautaServiceMock;

    @MockBean
    private ModelMapper modelMapperMock;

    private PautaDto pautaDto;
    private Pauta pauta;
    private PautaConsolidadaDto pautaConsolidadaDto;
    private Date dataCriacao;

    @Before
    public void inicializarContexto() {
        pautaDto = PautaDtoBuilder.umaPautaQualquer().build();
        pauta = PautaBuilder.umaPautaQualquer().build();
        dataCriacao = new Date();
        pautaConsolidadaDto = PautaConsolidadoDtoBuilder
                .umaPautaConsolidadaQualquer()
                .comDataCriacao(dataCriacao)
                .build();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsPautasEsperadas() throws Exception {
        BDDMockito.given(pautaServiceMock.listarTodos()).willReturn(Collections.singletonList(pauta));
        BDDMockito.given(modelMapperMock.map(pauta, PautaDto.class)).willReturn(pautaDto);

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].descricao").value(pautaDto.getDescricao()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhPautaEsperada() throws Exception {
        BDDMockito.given(modelMapperMock.map(any(PautaDto.class), eq(Pauta.class))).willReturn(pauta);
        BDDMockito.given(pautaServiceMock.cadastrar(any(Pauta.class))).willReturn(pauta);
        BDDMockito.given(modelMapperMock.map(any(Pauta.class), eq(PautaDto.class))).willReturn(pautaDto);

        ResultActions resultActions = cadastrarPauta();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descricao").value(pautaDto.getDescricao()));
    }

    @Test
    public void aoBuscarPorIdDeveriaRetornarAhPautaConsolidadaEsperada() throws Exception {
        BDDMockito.given(pautaServiceMock.buscarPorId(any(Long.class))).willReturn(pauta);
        BDDMockito.given(modelMapperMock.map(any(Pauta.class), eq(PautaConsolidadaDto.class))).willReturn(pautaConsolidadaDto);

        ResultActions resultActions = buscarPorId();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value(pautaConsolidadaDto.getDescricao()))
                .andExpect(jsonPath("$.dataCriacao").value(converter(pautaConsolidadaDto.getDataCriacao())))
                .andExpect(jsonPath("$.statusAssembleia").value(pautaConsolidadaDto.getStatusAssembleia().name()))
                .andExpect(jsonPath("$.quantidadeVotosSim").value(pautaConsolidadaDto.getQuantidadeVotosSim()))
                .andExpect(jsonPath("$.quantidadeVotosNao").value(pautaConsolidadaDto.getQuantidadeVotosNao()));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_PAUTAS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions cadastrarPauta() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(URI_V1_API_PAUTAS)
                .content(new ObjectMapper().writeValueAsString(pautaDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions buscarPorId() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(URI_V1_API_PAUTAS + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private String converter(Date data) {
        return MessageFormat.format("{0,date,dd/MM/yyyy HH:mm:ss}", data);
    }
}