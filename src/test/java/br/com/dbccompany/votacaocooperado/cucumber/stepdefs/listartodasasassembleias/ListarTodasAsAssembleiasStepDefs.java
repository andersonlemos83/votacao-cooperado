package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasasassembleias;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssembleiaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssembleiaVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class ListarTodasAsAssembleiasStepDefs extends StepDefs {

    private final AssembleiaFuncionalidade assembleiaFuncionalidade;
    private final AssembleiaVerificador assembleiaVerificador;

    @Quando("^listar todas as assembleias$")
    public void listarTodasAsAssembleias() throws Exception {
        ResultActions resultActions = assembleiaFuncionalidade.listarTodas();
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria retornar as seguintes assembleias$")
    public void deveriaRetornarAsSeguintesAssembleias(List<AssembleiaDataTable> assembleiasDataTable) throws Exception {
        assembleiaVerificador.verificar(assembleiasDataTable, transicaoDataTable.getResponse());
    }
}