package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.builder.PautaBuilder;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PautaContexto {

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void cadastrar(List<PautaDataTable> pautasDataTable) {
        pautasDataTable.forEach(pautaDataTable -> {
            Pauta pauta = PautaBuilder.umaPauta()
                    .comId(pautaDataTable.getId())
                    .comDecricao(pautaDataTable.getDescricao())
                    .build();
            pautaRepositoryTestHelper.saveAndFlush(pauta);
        });
    }
}