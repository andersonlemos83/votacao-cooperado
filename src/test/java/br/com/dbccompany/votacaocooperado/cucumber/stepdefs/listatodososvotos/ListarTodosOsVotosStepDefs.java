package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listatodososvotos;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.VotoFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.VotoVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListarTodosOsVotosStepDefs extends StepDefs {

    @Autowired
    private VotoFuncionalidade votoFuncionalidade;

    @Autowired
    private VotoVerificador votoVerificador;

    @Quando("^listar todos os votos$")
    public void listarTodosOsVotos() throws Exception {
        retorno = votoFuncionalidade.listarTodas();
    }

    @Entao("^deveria retornar os seguintes votos$")
    public void deveriaRetornarOsSeguintesVotos(List<VotoDataTable> votosDataTable) throws Exception {
        votoVerificador.verificar(votosDataTable, retorno);
    }
}