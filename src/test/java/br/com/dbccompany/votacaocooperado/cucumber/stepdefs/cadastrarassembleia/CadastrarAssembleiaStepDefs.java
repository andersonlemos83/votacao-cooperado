package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.cadastrarassembleia;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.AssembleiaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.AssembleiaVerificador;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CadastrarAssembleiaStepDefs extends StepDefs {

    @Autowired
    private AssembleiaFuncionalidade assembleiaFuncionalidade;

    @Autowired
    private AssembleiaVerificador assembleiaVerificador;

    private final AssembleiaDataTable assembleiaDataTable = new AssembleiaDataTable();

    @Dado("^que seja informado o tempo de duracao de \"([^\"]*)\" minutos$")
    public void queSejaInformadoOhTempoDeDuracaoDeMinutos(Integer tempoDuracao) {
        assembleiaDataTable.setTempoDuracao(Optional.ofNullable(tempoDuracao).orElse(0));
    }

    @E("^que seja informada a pauta \"([^\"]*)\"$")
    public void queSejaInformadaAhPauta(Long idPauta) {
        assembleiaDataTable.setIdPauta(idPauta);
    }

    @Quando("^cadastrar assembleia$")
    public void cadastrarAssembleia() throws Exception {
        retorno = assembleiaFuncionalidade.cadastrar(assembleiaDataTable);
    }

    @Entao("^deveria cadastrar a seguinte assembleia$")
    public void deveriaCadastrarAhSeguinteAssembleia(List<AssembleiaDataTable> assembleiasDataTable) throws Exception {
        assembleiaVerificador.verificar(assembleiasDataTable.stream().findFirst().get(), retorno);
    }
}