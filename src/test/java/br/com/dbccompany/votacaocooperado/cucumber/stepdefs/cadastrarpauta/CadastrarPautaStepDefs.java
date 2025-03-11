package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarpauta;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

public class CadastrarPautaStepDefs extends StepDefs {

    @Autowired
    private PautaFuncionalidade pautaFuncionalidade;

    @Autowired
    private PautaVerificador pautaVerificador;

    private final PautaDataTable pautaDataTable = new PautaDataTable();

    @Dado("^que seja informado a descricao \"([^\"]*)\"$")
    public void queSejaInformadoAhDescricao(String descricao) {
        pautaDataTable.setDescricao(descricao);
    }

    @Quando("^cadastrar pauta$")
    public void cadastrarPauta() throws Exception {
        retorno = pautaFuncionalidade.cadastrar(pautaDataTable);
    }

    @Entao("^deveria cadastrar a seguinte pauta \"([^\"]*)\"$")
    public void deveriaCadastrarAhSeguintePauta(String descricao) throws Exception {
        pautaVerificador.verificar(descricao, retorno);
    }
}