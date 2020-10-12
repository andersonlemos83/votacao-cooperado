package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasasassembleias;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssembleiaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssembleiaVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class ListarTodasAsAssembleiasStepDefs extends StepDefs {

    @Autowired
    private AssembleiaFuncionalidade assembleiaFuncionalidade;

    @Autowired
    private AssembleiaVerificador assembleiaVerificador;

    private ResultActions retorno;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Quando("^listar todas as assembleias$")
    public void listarTodasAsAssembleias() throws Exception {
        retorno = assembleiaFuncionalidade.listarTodas();
    }

    @Entao("^deveria retornar as seguintes assembleias$")
    public void deveriaRetornarAsSeguintesAssembleias(List<AssembleiaDataTable> assembleiasDataTable) throws Exception {
        assembleiaVerificador.verificar(assembleiasDataTable, retorno);
    }
}