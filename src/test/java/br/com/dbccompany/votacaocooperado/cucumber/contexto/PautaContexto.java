package br.com.dbccompany.votacaocooperado.cucumber.contexto;

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
        for (PautaDataTable pautaDataTable : pautasDataTable) {
            Pauta pauta = converter(pautaDataTable);
            pautaRepositoryTestHelper.save(pauta);
        }
    }

    private Pauta converter(PautaDataTable pautaDataTable) {
        Pauta pauta = new Pauta();
        pauta.setId(pautaDataTable.getId());
        pauta.setDescricao(pautaDataTable.getDescricao());
        return pauta;
    }
}
