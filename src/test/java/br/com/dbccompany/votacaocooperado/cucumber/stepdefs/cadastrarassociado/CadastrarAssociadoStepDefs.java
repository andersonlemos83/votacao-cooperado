package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarassociado;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssociadoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssociadoVerificador;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class CadastrarAssociadoStepDefs extends StepDefs {

    private final AssociadoFuncionalidade associadoFuncionalidade;
    private final AssociadoVerificador associadoVerificador;

    @Dado("^que seja informado o nome \"([^\"]*)\"$")
    public void queSejaInformadoOhNome(String nome) {
        transicaoDataTable.getAssociadoDataTable().setNome(nome);
    }

    @E("^que seja informado o CPF \"([^\"]*)\"$")
    public void queSejaInformadoOhCpf(String cpf) {
        transicaoDataTable.getAssociadoDataTable().setCpf(cpf);
    }

    @Quando("^cadastrar associado$")
    public void cadastrarAssociado() throws Exception {
        ResultActions resultActions = associadoFuncionalidade.cadastrar(transicaoDataTable.getAssociadoDataTable());
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria cadastrar o seguinte associado$")
    public void deveriaCadastrarOhSeguinteAssociado(List<AssociadoDataTable> associadosDataTable) throws Exception {
        associadoVerificador.verificar(associadosDataTable.stream().findFirst().get(), transicaoDataTable.getResponse());
    }
}
