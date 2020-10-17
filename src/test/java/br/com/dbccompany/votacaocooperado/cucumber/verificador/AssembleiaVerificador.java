package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static br.com.dbccompany.votacaocooperado.util.AssertUtil.assertData;
import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class AssembleiaVerificador {

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    @Autowired
    private AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;

    public void verificar(List<AssembleiaDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            Pauta pauta = pautaRepositoryTestHelper.findByDescricao(esperados.get(i).getDescricaoPauta());
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].tempoDuracao", i)).value(esperados.get(i).getTempoDuracao()))
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
        Optional<Assembleia> assembleiaOptional = Optional.ofNullable(assembleiaRepositoryTestHelper.findByPauta_Descricao(assembleiaDataTable.getDescricaoPauta()));
        return assembleiaOptional.orElse(new Assembleia());
    }
}