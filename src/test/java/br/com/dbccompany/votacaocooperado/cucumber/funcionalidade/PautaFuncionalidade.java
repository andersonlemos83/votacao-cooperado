package br.com.dbccompany.votacaocooperado.cucumber.funcionalidade;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class PautaFuncionalidade {

    private static final String API_PAUTA = "/api/pauta";

    @Autowired
    private MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(API_PAUTA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions buscarPorId(Long id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(API_PAUTA + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions cadastrar(PautaDataTable pautaDataTable) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(API_PAUTA)
                .content(new ObjectMapper().writeValueAsString(pautaDataTable))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }
}