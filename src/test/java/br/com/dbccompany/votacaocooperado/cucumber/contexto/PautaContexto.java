package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PautaContexto {

    private final PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void cadastrar(List<PautaDataTable> pautasDataTable) {
        List<Pauta> pautas = pautasDataTable.stream().map(this::gerarPauta).toList();
        pautaRepositoryTestHelper.saveAll(pautas);
    }

    private Pauta gerarPauta(PautaDataTable pautaDataTable) {
        Pauta pauta = new Pauta();
        BeanUtils.copyProperties(pautaDataTable, pauta);
        pauta.setId(null);
        return pauta;
    }
}