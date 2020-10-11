package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaConsolidadaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
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

    public void verificarConsolidado(List<PautaConsolidadaDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.descricao").value(esperados.get(i).getDescricaoPauta()))
                    .andExpect(jsonPath("$.statusAssembleia").value(esperados.get(i).getStatusAssembleia().name()))
                    .andExpect(jsonPath("$.quantidadeVotosSim").value(esperados.get(i).getQuantidadeVotosSim()))
                    .andExpect(jsonPath("$.quantidadeVotosNao").value(esperados.get(i).getQuantidadeVotosNao()));
        }
    }
}