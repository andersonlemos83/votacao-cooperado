package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarpauta;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

public class CadastrarPautaStepDefs extends StepDefs {

    @Autowired
    private PautaFuncionalidade pautaFuncionalidade;

    @Autowired
    private PautaVerificador pautaVerificador;

    private PautaDataTable pautaDataTable = new PautaDataTable();

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Dado("^que seja informado a descricao \"([^\"]*)\"$")
    public void queSejaInformadoAhDescricao(String descricao) {
        pautaDataTable.setDescricao(descricao);
    }

    @Quando("^cadastrar pauta$")
    public void cadastrarPauta() throws Exception {
        retorno = pautaFuncionalidade.cadastrar(pautaDataTable);
    }

    @Entao("^deveria retornar a seguinte pauta \"([^\"]*)\"$")
    public void deveriaRetornarAhSeguintePauta(String descricao) throws Exception {
        pautaVerificador.verificar(descricao, retorno);
    }
}