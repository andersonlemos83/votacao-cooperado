package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.text.MessageFormat.format;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class PautaVerificador {

    public void verificar(List<PautaDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].descricao", i)).value(esperados.get(i).getDescricao()));
        }
    }
}