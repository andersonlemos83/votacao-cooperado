package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas;

import br.com.dbccompany.votacaocooperado.cucumber.contexto.PautaContexto;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class ListarTodasAsPautasStepDefs extends StepDefs {

    @Autowired
    private PautaContexto pautaContexto;

    @Autowired
    private PautaFuncionalidade pautaFuncionalidade;

    @Autowired
    private PautaVerificador pautaVerificador;

    private ResultActions retorno;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Dado("^que existam as pautas cadastradas$")
    public void queExistamAsPautasCadastradas(List<PautaDataTable> pautasDataTable) {
        pautaContexto.cadastrar(pautasDataTable);
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