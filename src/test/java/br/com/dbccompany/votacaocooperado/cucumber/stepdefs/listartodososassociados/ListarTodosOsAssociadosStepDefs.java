package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodososassociados;

import br.com.dbccompany.votacaocooperado.cucumber.contexto.AssociadoContexto;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssociadoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssociadoVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class ListarTodosOsAssociadosStepDefs extends StepDefs {

    @Autowired
    private AssociadoContexto associadoContexto;

    @Autowired
    private AssociadoFuncionalidade associadoFuncionalidade;

    @Autowired
    private AssociadoVerificador associadoVerificador;

    private ResultActions retorno;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Dado("^que existam os associados cadastrados$")
    public void queExistamOsAssociadosCadastrados(List<AssociadoDataTable> associadosDataTable) {
        associadoContexto.cadastrar(associadosDataTable);
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