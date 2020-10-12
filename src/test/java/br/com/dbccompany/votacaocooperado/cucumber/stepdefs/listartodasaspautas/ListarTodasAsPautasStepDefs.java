package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class ListarTodasAsPautasStepDefs extends StepDefs {

    @Autowired
    private PautaFuncionalidade pautaFuncionalidade;

    @Autowired
    private PautaVerificador pautaVerificador;

    private ResultActions retorno;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Quando("^listar todas as pautas$")
    public void listarTodasAsPautas() throws Exception {
        retorno = pautaFuncionalidade.listarTodas();
    }

    @Entao("^deveria retornar as seguintes pautas$")
    public void deveriaRetornarAsSeguintesPautas(List<PautaDataTable> pautasDataTable) throws Exception {
        pautaVerificador.verificar(pautasDataTable, retorno);
    }
}