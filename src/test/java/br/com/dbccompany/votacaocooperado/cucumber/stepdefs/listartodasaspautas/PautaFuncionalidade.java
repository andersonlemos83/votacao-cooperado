package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class PautaFuncionalidade {

    @Autowired
    private MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}