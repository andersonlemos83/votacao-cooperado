package br.com.dbccompany.votacaocooperado.cucumber.funcionalidade;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssembleiaDataTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil.URI_V1_API_ASSEMBLEIAS;

@Component
@AllArgsConstructor
public class AssembleiaFuncionalidade {

    private final MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ASSEMBLEIAS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions cadastrar(AssembleiaDataTable assembleiaDataTable) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_V1_API_ASSEMBLEIAS)
                .content(new ObjectMapper().writeValueAsString(assembleiaDataTable))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }
}