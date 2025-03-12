package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarvoto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.VotoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.VotoVerificador;
import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class CadastrarVotoStepDefs extends StepDefs {

    private final VotoFuncionalidade votoFuncionalidade;
    private final VotoVerificador votoVerificador;

    @Dado("^que seja informado o associado \"([^\"]*)\"$")
    public void queSejaInformadoOhAssociado(Long idAssociado) {
        transicaoDataTable.getVotoDataTable().setIdAssociado(idAssociado);
    }

    @E("^que seja informado a assembleia \"([^\"]*)\"$")
    public void queSejaInformadaAhAssembleia(Long idAssembleia) {
        transicaoDataTable.getVotoDataTable().setIdAssembleia(idAssembleia);
    }

    @E("^que seja informado o voto \"([^\"]*)\"$")
    public void queSejaInformadoOhVoto(TipoVoto tipoVoto) {
        transicaoDataTable.getVotoDataTable().setTipoVoto(tipoVoto);
    }

    @Quando("^cadastrar voto$")
    public void cadastrarVoto() throws Exception {
        ResultActions resultActions = votoFuncionalidade.cadastrar(transicaoDataTable.getVotoDataTable());
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria cadastrar o seguinte voto$")
    public void deveriaCadastrarOhSeguinteVoto(List<VotoDataTable> votosDataTable) throws Exception {
        votoVerificador.verificar(votosDataTable.stream().findFirst().get(), transicaoDataTable.getResponse());
    }
}
