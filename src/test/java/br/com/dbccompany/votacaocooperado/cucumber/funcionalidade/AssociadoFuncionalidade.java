package br.com.dbccompany.votacaocooperado.cucumber.funcionalidade;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class AssociadoFuncionalidade {

    private static final String API_ASSOCIADO = "/api/associado";

    @Autowired
    private MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(API_ASSOCIADO)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions cadastrar(AssociadoDataTable associadoDataTable) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(API_ASSOCIADO)
                .content(new ObjectMapper().writeValueAsString(associadoDataTable))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }
}
