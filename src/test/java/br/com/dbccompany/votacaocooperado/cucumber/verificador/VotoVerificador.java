package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.VotoDataTable;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.helper.repository.AssembleiaRepositoryHelper;
import br.com.dbccompany.votacaocooperado.helper.repository.AssociadoRepositoryHelper;
import br.com.dbccompany.votacaocooperado.helper.repository.VotoRepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@AllArgsConstructor
public class VotoVerificador {

    private final AssociadoRepositoryHelper associadoRepositoryHelper;
    private final AssembleiaRepositoryHelper assembleiaRepositoryHelper;
    private final VotoRepositoryHelper votoRepositoryHelper;

    public void verificar(List<VotoDataTable> esperados, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isOk());

        for (int i = 0; i < esperados.size(); i++) {
            final VotoDataTable esperado = esperados.get(i);
            Associado associado = associadoRepositoryHelper.findByNome(esperado.getNomeAssociado());
            Assembleia assembleia = assembleiaRepositoryHelper.findByPauta_Descricao(esperado.getDescricaoPauta());
            retorno.andExpect(jsonPath(format("$.[{0}].tipoVoto", i)).value(esperado.getTipoVoto().name()))
                    .andExpect(jsonPath(format("$.[{0}].idAssociado", i)).value(associado.getId()))
                    .andExpect(jsonPath(format("$.[{0}].idAssembleia", i)).value(assembleia.getId()));
        }
    }

    public void verificar(VotoDataTable votoDataTable, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Voto voto = consultarVoto(votoDataTable);
        assertEquals(votoDataTable.getDescricaoPauta(), voto.getAssembleia().getPauta().getDescricao());
        assertEquals(votoDataTable.getNomeAssociado(), voto.getAssociado().getNome());
        assertEquals(votoDataTable.getTipoVoto(), voto.getTipoVoto());
    }

    private Voto consultarVoto(VotoDataTable votoDataTable) {
        Optional<Voto> votoOptional = Optional.ofNullable(votoRepositoryHelper.findByAssociado_NomeAndAndAssembleia_Pauta_Descricao(votoDataTable.getNomeAssociado(), votoDataTable.getDescricaoPauta()));
        return votoOptional.orElse(new Voto());
    }
}