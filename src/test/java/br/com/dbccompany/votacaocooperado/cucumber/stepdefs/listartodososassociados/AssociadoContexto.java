package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodososassociados;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociadoContexto {

    @Autowired
    private AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    public void cadastrar(List<AssociadoDataTable> associadosDataTable) {
        for (AssociadoDataTable associadoDataTable : associadosDataTable) {
            Associado associado = converter(associadoDataTable);
            associadoRepositoryTestHelper.save(associado);
        }
    }

    private Associado converter(AssociadoDataTable associadoDataTable) {
        Associado associado = new Associado();
        associado.setId(associadoDataTable.getId());
        associado.setNome(associadoDataTable.getNome());
        associado.setCpf(associadoDataTable.getCpf());
        return associado;
    }
}