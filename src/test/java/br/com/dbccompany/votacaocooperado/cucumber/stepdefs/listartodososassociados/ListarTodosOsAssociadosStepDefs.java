package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodososassociados;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssociadoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssociadoVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class ListarTodosOsAssociadosStepDefs extends StepDefs {

    private final AssociadoFuncionalidade associadoFuncionalidade;
    private final AssociadoVerificador associadoVerificador;

    @Quando("^listar todos os associados$")
    public void listarTodosOsAssociados() throws Exception {
        ResultActions resultActions = associadoFuncionalidade.listarTodas();
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria retornar os seguintes associados$")
    public void deveriaRetornarOsSeguintesAssociados(List<AssociadoDataTable> associadosDataTable) throws Exception {
        associadoVerificador.verificar(associadosDataTable, transicaoDataTable.getResponse());
    }
}