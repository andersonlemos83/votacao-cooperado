package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.text.MessageFormat.format;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class AssembleiaVerificador {

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void verificar(List<AssembleiaDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            Pauta pauta = pautaRepositoryTestHelper.findByDescricao(esperados.get(i).getDescricaoPauta());
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].tempoDuracao", i)).value(esperados.get(i).getTempoDuracao()))
                    .andExpect(jsonPath(format("$.[{0}].idPauta", i)).value(pauta.getId()));
        }
    }
}