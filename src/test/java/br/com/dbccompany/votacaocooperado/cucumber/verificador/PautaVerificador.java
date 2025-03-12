package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaConsolidadaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@AllArgsConstructor
public class PautaVerificador {

    private final PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void verificar(List<PautaDataTable> esperados, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isOk());

        for (int i = 0; i < esperados.size(); i++) {
            retorno.andExpect(jsonPath(format("$.[{0}].descricao", i)).value(esperados.get(i).getDescricao()));
        }
    }

    public void verificarConsolidado(List<PautaConsolidadaDataTable> esperados, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isOk());

        for (final PautaConsolidadaDataTable esperado : esperados) {
            retorno.andExpect(jsonPath("$.descricao").value(esperado.getDescricaoPauta()))
                    .andExpect(jsonPath("$.statusAssembleia").value(esperado.getStatusAssembleia().name()))
                    .andExpect(jsonPath("$.quantidadeVotosSim").value(esperado.getQuantidadeVotosSim()))
                    .andExpect(jsonPath("$.quantidadeVotosNao").value(esperado.getQuantidadeVotosNao()));
        }
    }

    public void verificar(String descricao, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Pauta pauta = pautaRepositoryTestHelper.findAll().stream().findFirst().orElse(new Pauta());
        assertEquals(descricao, pauta.getDescricao());
    }
}