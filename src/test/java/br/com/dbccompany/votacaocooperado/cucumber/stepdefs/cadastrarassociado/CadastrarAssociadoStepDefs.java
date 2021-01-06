package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarassociado;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssociadoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssociadoVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CadastrarAssociadoStepDefs extends StepDefs {

    @Autowired
    private AssociadoFuncionalidade associadoFuncionalidade;

    @Autowired
    private AssociadoVerificador associadoVerificador;

    private final AssociadoDataTable associadoDataTable = new AssociadoDataTable();

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Dado("^que seja informado o nome \"([^\"]*)\"$")
    public void queSejaInformadoOhNome(String nome) {
        associadoDataTable.setNome(nome);
    }

    @E("^que seja informado o CPF \"([^\"]*)\"$")
    public void queSejaInformadoOhCpf(String cpf) {
        associadoDataTable.setCpf(cpf);
    }

    @Quando("^cadastrar associado$")
    public void cadastrarAssociado() throws Exception {
        retorno = associadoFuncionalidade.cadastrar(associadoDataTable);
    }

    @Entao("^deveria cadastrar o seguinte associado$")
    public void deveriaCadastrarOhSeguinteAssociado(List<AssociadoDataTable> associadosDataTable) throws Exception {
        associadoVerificador.verificar(associadosDataTable.stream().findFirst().get(), retorno);
    }
}
