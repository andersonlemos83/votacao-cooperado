package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssociado;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
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
public class AssociadoResourceTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private AssociadoService associadoServiceMock;

    @MockBean
    private ConversorAssociado conversorAssociadoMock;

    private AssociadoDto associadoDto;
    private Associado associado;

    @Before
    public void inicializarContexto() {
        associadoDto = gerarAssociadoDto();
        associado = gerarAssociado();
    }

    @Test
    public void aoListarTodosDeveriaRetornarAsAssociadosEsperadas() throws Exception {
        BDDMockito.given(associadoServiceMock.listarTodos()).willReturn(Arrays.asList(associado));
        BDDMockito.given(conversorAssociadoMock.converter(Mockito.any(List.class))).willReturn(Arrays.asList(associadoDto));

        ResultActions resultActions = listarTodos();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].nome").value(associadoDto.getNome()))
                .andExpect(jsonPath("$.[0].cpf").value(associadoDto.getCpf()));
    }

    @Test
    public void aoCadastrarDeveriaRetornarAhAssociadoEsperada() throws Exception {
        BDDMockito.given(conversorAssociadoMock.converter(Mockito.any(AssociadoDto.class))).willReturn(associado);
        BDDMockito.given(associadoServiceMock.cadastrar(Mockito.any(Associado.class))).willReturn(associado);
        BDDMockito.given(conversorAssociadoMock.converter(Mockito.any(Associado.class))).willReturn(associadoDto);

        ResultActions resultActions = cadastrarAssociado();

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(associadoDto.getNome()))
                .andExpect(jsonPath("$.cpf").value(associadoDto.getCpf()));
    }

    private ResultActions cadastrarAssociado() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/api/associado")
                .content("{\"nome\":\"Anderson\",\"cpf\":\"05551876044\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    private ResultActions listarTodos() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/api/associado")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private AssociadoDto gerarAssociadoDto() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setNome("Anderson");
        associadoDto.setCpf("05551876044");
        return associadoDto;
    }

    private Associado gerarAssociado() {
        Associado associado = new Associado();
        associado.setNome("Anderson");
        associado.setCpf("05551876044");
        return associado;
    }
}