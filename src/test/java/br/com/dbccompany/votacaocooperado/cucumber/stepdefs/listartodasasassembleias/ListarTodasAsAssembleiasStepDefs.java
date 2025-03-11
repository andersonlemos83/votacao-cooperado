package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasasassembleias;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssembleiaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssembleiaVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListarTodasAsAssembleiasStepDefs extends StepDefs {

    @Autowired
    private AssembleiaFuncionalidade assembleiaFuncionalidade;

    @Autowired
    private AssembleiaVerificador assembleiaVerificador;

    @Quando("^listar todas as assembleias$")
    public void listarTodasAsAssembleias() throws Exception {
        retorno = assembleiaFuncionalidade.listarTodas();
    }

    @Entao("^deveria retornar as seguintes assembleias$")
    public void deveriaRetornarAsSeguintesAssembleias(List<AssembleiaDataTable> assembleiasDataTable) throws Exception {
        assembleiaVerificador.verificar(assembleiasDataTable, retorno);
    }
}