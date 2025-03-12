package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.helper.repository.AssociadoRepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AssociadoContexto {

    private final AssociadoRepositoryHelper associadoRepositoryHelper;

    public void cadastrar(List<AssociadoDataTable> associadosDataTable) {
        List<Associado> associados = associadosDataTable.stream().map(this::gerarAssociado).toList();
        associadoRepositoryHelper.saveAll(associados);
    }

    private Associado gerarAssociado(AssociadoDataTable associadoDataTable) {
        Associado associado = new Associado();
        BeanUtils.copyProperties(associadoDataTable, associado);
        associado.setId(null);
        return associado;
    }
}