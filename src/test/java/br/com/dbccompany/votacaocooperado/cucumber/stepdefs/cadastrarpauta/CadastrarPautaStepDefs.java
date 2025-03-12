package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarpauta;

import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

@AllArgsConstructor
public class CadastrarPautaStepDefs extends StepDefs {

    private final PautaFuncionalidade pautaFuncionalidade;
    private final PautaVerificador pautaVerificador;

    @Dado("^que seja informado a descricao \"([^\"]*)\"$")
    public void queSejaInformadoAhDescricao(String descricao) {
        transicaoDataTable.getPautaDataTable().setDescricao(descricao);
    }

    @Quando("^cadastrar pauta$")
    public void cadastrarPauta() throws Exception {
        ResultActions resultActions = pautaFuncionalidade.cadastrar(transicaoDataTable.getPautaDataTable());
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria cadastrar a seguinte pauta \"([^\"]*)\"$")
    public void deveriaCadastrarAhSeguintePauta(String descricao) throws Exception {
        pautaVerificador.verificar(descricao, transicaoDataTable.getResponse());
    }
}