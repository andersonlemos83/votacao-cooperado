package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class ListarTodasAsPautasStepDefs extends StepDefs {

    private final PautaFuncionalidade pautaFuncionalidade;
    private final PautaVerificador pautaVerificador;

    @Quando("^listar todas as pautas$")
    public void listarTodasAsPautas() throws Exception {
        ResultActions resultActions = pautaFuncionalidade.listarTodas();
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria retornar as seguintes pautas$")
    public void deveriaRetornarAsSeguintesPautas(List<PautaDataTable> pautasDataTable) throws Exception {
        pautaVerificador.verificar(pautasDataTable, transicaoDataTable.getResponse());
    }
}