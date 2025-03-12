package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarassembleia;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssembleiaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssembleiaVerificador;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CadastrarAssembleiaStepDefs extends StepDefs {

    private final AssembleiaFuncionalidade assembleiaFuncionalidade;
    private final AssembleiaVerificador assembleiaVerificador;

    @Dado("^que seja informado o tempo de duracao de \"([^\"]*)\" minutos$")
    public void queSejaInformadoOhTempoDeDuracaoDeMinutos(Integer tempoDuracao) {
        transicaoDataTable.getAssembleiaDataTable().setTempoDuracao(Optional.ofNullable(tempoDuracao).orElse(0));
    }

    @E("^que seja informada a pauta \"([^\"]*)\"$")
    public void queSejaInformadaAhPauta(Long idPauta) {
        transicaoDataTable.getAssembleiaDataTable().setIdPauta(idPauta);
    }

    @Quando("^cadastrar assembleia$")
    public void cadastrarAssembleia() throws Exception {
        ResultActions resultActions = assembleiaFuncionalidade.cadastrar(transicaoDataTable.getAssembleiaDataTable());
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria cadastrar a seguinte assembleia$")
    public void deveriaCadastrarAhSeguinteAssembleia(List<AssembleiaDataTable> assembleiasDataTable) throws Exception {
        assembleiaVerificador.verificar(assembleiasDataTable.stream().findFirst().get(), transicaoDataTable.getResponse());
    }
}