package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listatodososvotos;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.VotoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.VotoVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class ListarTodosOsVotosStepDefs extends StepDefs {

    private final VotoFuncionalidade votoFuncionalidade;
    private final VotoVerificador votoVerificador;

    @Quando("^listar todos os votos$")
    public void listarTodosOsVotos() throws Exception {
        ResultActions resultActions = votoFuncionalidade.listarTodas();
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria retornar os seguintes votos$")
    public void deveriaRetornarOsSeguintesVotos(List<VotoDataTable> votosDataTable) throws Exception {
        votoVerificador.verificar(votosDataTable, transicaoDataTable.getResponse());
    }
}