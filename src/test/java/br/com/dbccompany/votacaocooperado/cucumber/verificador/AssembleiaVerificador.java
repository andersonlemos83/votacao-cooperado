package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.helper.repository.AssembleiaRepositoryHelper;
import br.com.dbccompany.votacaocooperado.helper.repository.PautaRepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static br.com.dbccompany.votacaocooperado.helper.util.AssertUtil.assertData;
import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@AllArgsConstructor
public class AssembleiaVerificador {

    private final PautaRepositoryHelper pautaRepositoryHelper;
    private final AssembleiaRepositoryHelper assembleiaRepositoryHelper;

    public void verificar(List<AssembleiaDataTable> esperados, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isOk());

        for (int i = 0; i < esperados.size(); i++) {
            final AssembleiaDataTable esperado = esperados.get(i);
            Pauta pauta = pautaRepositoryHelper.findByDescricao(esperado.getDescricaoPauta());
            retorno.andExpect(jsonPath(format("$.[{0}].tempoDuracao", i)).value(esperado.getTempoDuracao()))
                    .andExpect(jsonPath(format("$.[{0}].idPauta", i)).value(pauta.getId()));
        }
    }

    public void verificar(AssembleiaDataTable assembleiaDataTable, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Assembleia assembleia = consultarAssembleia(assembleiaDataTable);
        assertEquals(assembleiaDataTable.getDescricaoPauta(), assembleia.getPauta().getDescricao());
        assertEquals(assembleiaDataTable.getTempoDuracao(), assembleia.getTempoDuracao());
        assertData(assembleiaDataTable.getDataCriacao(), assembleia.getDataCriacao());
    }

    private Assembleia consultarAssembleia(AssembleiaDataTable assembleiaDataTable) {
        Optional<Assembleia> assembleiaOptional = Optional.ofNullable(assembleiaRepositoryHelper.findByPauta_Descricao(assembleiaDataTable.getDescricaoPauta()));
        return assembleiaOptional.orElse(new Assembleia());
    }
}