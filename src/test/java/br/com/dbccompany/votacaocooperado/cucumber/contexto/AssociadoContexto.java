package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AssociadoContexto {

    private final AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    public void cadastrar(List<AssociadoDataTable> associadosDataTable) {
        List<Associado> associados = associadosDataTable.stream().map(this::gerarAssociado).toList();
        associadoRepositoryTestHelper.saveAll(associados);
    }

    private Associado gerarAssociado(AssociadoDataTable associadoDataTable) {
        Associado associado = new Associado();
        BeanUtils.copyProperties(associadoDataTable, associado);
        associado.setId(null);
        return associado;
    }
}