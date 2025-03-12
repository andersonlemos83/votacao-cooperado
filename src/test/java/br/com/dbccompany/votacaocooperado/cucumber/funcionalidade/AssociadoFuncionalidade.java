package br.com.dbccompany.votacaocooperado.cucumber.funcionalidade;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil.URI_V1_API_ASSOCIADOS;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class AssociadoFuncionalidade {

    private final MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ASSOCIADOS)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }

    public ResultActions cadastrar(AssociadoDataTable associadoDataTable) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(ConstanteUtil.URI_V1_API_ASSOCIADOS)
                .content(new ObjectMapper().writeValueAsString(associadoDataTable))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }
}
