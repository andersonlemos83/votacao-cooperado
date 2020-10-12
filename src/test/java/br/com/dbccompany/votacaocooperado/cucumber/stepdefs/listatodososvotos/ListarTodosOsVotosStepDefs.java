package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listatodososvotos;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.VotoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.VotoVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class ListarTodosOsVotosStepDefs extends StepDefs {

    @Autowired
    private VotoFuncionalidade votoFuncionalidade;

    @Autowired
    private VotoVerificador votoVerificador;

    private ResultActions retorno;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Quando("^listar todos os votos$")
    public void listarTodosOsVotos() throws Exception {
        retorno = votoFuncionalidade.listarTodas();
    }

    @Entao("^deveria retornar os seguintes votos$")
    public void deveriaRetornarOsSeguintesVotos(List<VotoDataTable> votosDataTable) throws Exception {
        votoVerificador.verificar(votosDataTable, retorno);
    }
}