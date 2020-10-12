package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaConsolidadaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class PautaVerificador {

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void verificar(List<PautaDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].descricao", i)).value(esperados.get(i).getDescricao()));
        }
    }

    public void verificarConsolidado(List<PautaConsolidadaDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath("$.descricao").value(esperados.get(i).getDescricaoPauta()))
                    .andExpect(jsonPath("$.statusAssembleia").value(esperados.get(i).getStatusAssembleia().name()))
                    .andExpect(jsonPath("$.quantidadeVotosSim").value(esperados.get(i).getQuantidadeVotosSim()))
                    .andExpect(jsonPath("$.quantidadeVotosNao").value(esperados.get(i).getQuantidadeVotosNao()));
        }
    }

    public void verificar(String descricao, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Optional<Pauta> pautaOptional = pautaRepositoryTestHelper.findAll().stream().findFirst();
        assertEquals(descricao, pautaOptional.orElse(new Pauta()).getDescricao());
    }
}