package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.text.MessageFormat.format;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class AssociadoVerificador {
    public void verificar(List<AssociadoDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].nome", i)).value(esperados.get(i).getNome()))
                    .andExpect(jsonPath(format("$.[{0}].cpf", i)).value(esperados.get(i).getCpf()));
        }
    }
}