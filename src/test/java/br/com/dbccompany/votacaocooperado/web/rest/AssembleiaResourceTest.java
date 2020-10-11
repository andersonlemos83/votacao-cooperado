package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssembleia;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotacaoCooperadoApplication.class)
@AutoConfigureMockMvc
public class AssembleiaResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private AssembleiaService assembleiaServiceMock;

    @MockBean
    private ConversorAssembleia conversorAssembleiaMock;

    private AssembleiaDto assembleiaDto;
    private Assembleia assembleia;
    private Date dataCriacao;

    @Before
    public void inicializarContexto() {
        dataCriacao = new Date();
        assembleiaDto = gerarAssembleiaDto();
        assembleia = gerarAssembleia();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsAssembleiasEsperadas() throws Exception {
        BDDMockito.given(assembleiaServiceMock.listarTodos()).willReturn(Arrays.asList(assembleia));
        BDDMockito.given(conversorAssembleiaMock.converter(Mockito.any(List.class))).willReturn(Arrays.asList(assembleiaDto));

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].dataCriacao").value(converter(assembleiaDto.getDataCriacao())))
                .andExpect(jsonPath("$.[0].tempoDuracao").value(assembleiaDto.getTempoDuracao()))
                .andExpect(jsonPath("$.[0].idPauta").value(assembleiaDto.getIdPauta()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhAssembleiaEsperada() throws Exception {
        BDDMockito.given(conversorAssembleiaMock.converter(Mockito.any(AssembleiaDto.class))).willReturn(assembleia);
        BDDMockito.given(assembleiaServiceMock.cadastrar(Mockito.any(Assembleia.class))).willReturn(assembleia);
        BDDMockito.given(conversorAssembleiaMock.converter(Mockito.any(Assembleia.class))).willReturn(assembleiaDto);

        ResultActions resultActions = cadastrarAssembleia();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dataCriacao").value(converter(assembleiaDto.getDataCriacao())))
                .andExpect(jsonPath("$.tempoDuracao").value(assembleiaDto.getTempoDuracao()))
                .andExpect(jsonPath("$.idPauta").value(assembleiaDto.getIdPauta()));
    }

    private ResultActions cadastrarAssembleia() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/api/assembleia")
                .content("{\"tempoDuracao\":\"2\",\"idPauta\":\"1\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/api/assembleia")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private AssembleiaDto gerarAssembleiaDto() {
        AssembleiaDto assembleiaDto = new AssembleiaDto();
        assembleiaDto.setDataCriacao(dataCriacao);
        assembleiaDto.setTempoDuracao(2);
        assembleiaDto.setIdPauta(1l);
        return assembleiaDto;
    }

    private Assembleia gerarAssembleia() {
        Assembleia assembleia = new Assembleia();
        assembleia.setDataCriacao(dataCriacao);
        assembleia.setTempoDuracao(2);
        assembleia.setPauta(new Pauta(1l));
        return assembleia;
    }

    private String converter(Date data) {
        return MessageFormat.format("{0,date,dd-MM-yyyy'T'HH:mm:ss.SSSZ}", data);
    }
}
