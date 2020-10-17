package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.builder.AssociadoBuilder;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociadoContexto {

    @Autowired
    private AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    public void cadastrar(List<AssociadoDataTable> associadosDataTable) {
        associadosDataTable.forEach(associadoDataTable -> {
            Associado associado = AssociadoBuilder.umAssociado()
                    .comId(associadoDataTable.getId())
                    .comNome(associadoDataTable.getNome())
                    .comCpf(associadoDataTable.getCpf())
                    .build();
            associadoRepositoryTestHelper.saveAndFlush(associado);
        });
    }
}