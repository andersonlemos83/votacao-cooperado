package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodososassociados;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssociadoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssociadoVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListarTodosOsAssociadosStepDefs extends StepDefs {

    @Autowired
    private AssociadoFuncionalidade associadoFuncionalidade;

    @Autowired
    private AssociadoVerificador associadoVerificador;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Quando("^listar todos os associados$")
    public void listarTodosOsAssociados() throws Exception {
        retorno = associadoFuncionalidade.listarTodas();
    }

    @Entao("^deveria retornar os seguintes associados$")
    public void deveriaRetornarOsSeguintesAssociados(List<AssociadoDataTable> associadosDataTable) throws Exception {
        associadoVerificador.verificar(associadosDataTable, retorno);
    }
}