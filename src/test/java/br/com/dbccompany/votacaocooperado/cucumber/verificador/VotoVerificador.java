package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.VotoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
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
public class VotoVerificador {

    @Autowired
    private AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    @Autowired
    private AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;

    @Autowired
    private VotoRepositoryTestHelper votoRepositoryTestHelper;

    public void verificar(List<VotoDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            Associado associado = associadoRepositoryTestHelper.findByNome(esperados.get(i).getNomeAssociado());
            Assembleia assembleia = assembleiaRepositoryTestHelper.findByPauta_Descricao(esperados.get(i).getDescricaoPauta());
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].tipoVoto", i)).value(esperados.get(i).getTipoVoto().name()))
                    .andExpect(jsonPath(format("$.[{0}].idAssociado", i)).value(associado.getId()))
                    .andExpect(jsonPath(format("$.[{0}].idAssembleia", i)).value(assembleia.getId()));
        }
    }

    public void verificar(VotoDataTable votoDataTable, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Optional<Voto> votoOptional = Optional.ofNullable(votoRepositoryTestHelper.findByAssociado_NomeAndAndAssembleia_Pauta_Descricao(votoDataTable.getNomeAssociado(), votoDataTable.getDescricaoPauta()));
        Voto voto = votoOptional.orElse(new Voto());
        assertEquals(votoDataTable.getDescricaoPauta(), voto.getAssembleia().getPauta().getDescricao());
        assertEquals(votoDataTable.getNomeAssociado(), voto.getAssociado().getNome());
        assertEquals(votoDataTable.getTipoVoto(), voto.getTipoVoto());
    }
}