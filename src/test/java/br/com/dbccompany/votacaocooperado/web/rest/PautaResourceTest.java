package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorPauta;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;
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
    private ConversorPauta conversorPautaMock;

    private PautaDto pautaDto;
    private Pauta pauta;
    private PautaConsolidadaDto pautaConsolidadaDto;
    private Date dataCriacao;

    @Before
    public void inicializarContexto() {
        pautaDto = gerarPautaDto();
        pauta = gerarPauta();
        dataCriacao = new Date();
        pautaConsolidadaDto = gerarPautaConsolidadaDto();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsPautasEsperadas() throws Exception {
        BDDMockito.given(pautaServiceMock.listarTodos()).willReturn(Arrays.asList(pauta));
        BDDMockito.given(conversorPautaMock.converter(Mockito.any(List.class))).willReturn(Arrays.asList(pautaDto));

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].descricao").value(pautaDto.getDescricao()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhPautaEsperada() throws Exception {
        BDDMockito.given(conversorPautaMock.converter(Mockito.any(PautaDto.class))).willReturn(pauta);
        BDDMockito.given(pautaServiceMock.cadastrar(Mockito.any(Pauta.class))).willReturn(pauta);
        BDDMockito.given(conversorPautaMock.converter(Mockito.any(Pauta.class))).willReturn(pautaDto);

        ResultActions resultActions = cadastrarPauta();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descricao").value(pautaDto.getDescricao()));
    }

    @Test
    public void aoBuscarPorIdDeveriaRetornarAhPautaConsolidadaEsperada() throws Exception {
        BDDMockito.given(pautaServiceMock.buscarPorId(Mockito.any(Long.class))).willReturn(pauta);
        BDDMockito.given(conversorPautaMock.converterParaConsolidada(Mockito.any(Pauta.class))).willReturn(pautaConsolidadaDto);

        ResultActions resultActions = buscarPorId();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value(pautaConsolidadaDto.getDescricao()))
                .andExpect(jsonPath("$.dataCriacao").value(converter(pautaConsolidadaDto.getDataCriacao())))
                .andExpect(jsonPath("$.statusAssembleia").value(pautaConsolidadaDto.getStatusAssembleia().name()))
                .andExpect(jsonPath("$.quantidadeVotosSim").value(pautaConsolidadaDto.getQuantidadeVotosSim()))
                .andExpect(jsonPath("$.quantidadeVotosNao").value(pautaConsolidadaDto.getQuantidadeVotosNao()));
    }

    private ResultActions cadastrarPauta() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/api/pauta")
                .content("{\"descricao\":\"Votar sobre novo fundo de investimento.\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/api/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions buscarPorId() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/api/pauta/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private PautaDto gerarPautaDto() {
        PautaDto pautaDto = new PautaDto();
        pautaDto.setDescricao("Votar sobre novo fundo de investimento.");
        return pautaDto;
    }

    private Pauta gerarPauta() {
        Pauta pauta = new Pauta();
        pauta.setDescricao("Votar sobre novo fundo de investimento.");
        return pauta;
    }

    private PautaConsolidadaDto gerarPautaConsolidadaDto() {
        PautaConsolidadaDto pautaConsolidadaDto = new PautaConsolidadaDto();
        pautaConsolidadaDto.setDescricao("Votar sobre novo fundo de investimento.");
        pautaConsolidadaDto.setDataCriacao(dataCriacao);
        pautaConsolidadaDto.setStatusAssembleia(ABERTA);
        pautaConsolidadaDto.setQuantidadeVotosSim(1);
        pautaConsolidadaDto.setQuantidadeVotosNao(3);
        return pautaConsolidadaDto;
    }

    private String converter(Date data) {
        return MessageFormat.format("{0,date,dd/MM/yyyy HH:mm:ss}", data);
    }
}