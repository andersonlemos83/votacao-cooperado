package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarvoto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.VotoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.VotoVerificador;
import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CadastrarVotoStepDefs extends StepDefs {

    @Autowired
    private VotoFuncionalidade votoFuncionalidade;

    @Autowired
    private VotoVerificador votoVerificador;

    private final VotoDataTable votoDataTable = new VotoDataTable();

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Dado("^que seja informado o associado \"([^\"]*)\"$")
    public void queSejaInformadoOhAssociado(Long idAssociado) {
        votoDataTable.setIdAssociado(idAssociado);
    }

    @E("^que seja informado a assembleia \"([^\"]*)\"$")
    public void queSejaInformadaAhAssembleia(Long idAssembleia) {
        votoDataTable.setIdAssembleia(idAssembleia);
    }

    @E("^que seja informado o voto \"([^\"]*)\"$")
    public void queSejaInformadoOhVoto(TipoVoto tipoVoto) {
        votoDataTable.setTipoVoto(tipoVoto);
    }

    @Quando("^cadastrar voto$")
    public void cadastrarVoto() throws Exception {
        retorno = votoFuncionalidade.cadastrar(votoDataTable);
    }

    @Entao("^deveria cadastrar o seguinte voto$")
    public void deveriaCadastrarOhSeguinteVoto(List<VotoDataTable> votosDataTable) throws Exception {
        votoVerificador.verificar(votosDataTable.stream().findFirst().get(), retorno);
    }
}
