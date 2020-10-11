package br.com.dbccompany.votacaocooperado.cucumber.funcionalidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class VotoFuncionalidade {

    @Autowired
    private MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/voto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}