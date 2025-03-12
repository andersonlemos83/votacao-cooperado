package br.com.dbccompany.votacaocooperado.cucumber.funcionalidade;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.PautaDataTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.dbccompany.votacaocooperado.helper.util.ConstanteUtil.URI_V1_API_PAUTAS;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class PautaFuncionalidade {

    private final MockMvc mockMvc;

    public ResultActions listarTodas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_V1_API_PAUTAS)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }

    public ResultActions buscarPorId(Long id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_V1_API_PAUTAS + "/" + id)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }

    public ResultActions cadastrar(PautaDataTable pautaDataTable) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(URI_V1_API_PAUTAS)
                .content(new ObjectMapper().writeValueAsString(pautaDataTable))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));
    }
}