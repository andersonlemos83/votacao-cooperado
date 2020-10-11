package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.buscarpautaconsolidadaporid;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaConsolidadaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class BuscarPautaConsolidadaPorIdStepDefs extends StepDefs {

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    @Autowired
    private PautaFuncionalidade pautaFuncionalidade;

    @Autowired
    private PautaVerificador pautaVerificador;

    private ResultActions retorno;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Quando("^buscar pauta consolidada por ID \"([^\"]*)\"$")
    public void buscarPautaConsolidadaPorId(String descricaoPauta) throws Exception {
        Pauta pauta = pautaRepositoryTestHelper.findByDescricao(descricaoPauta);
        retorno = pautaFuncionalidade.buscarPorId(pauta.getId());
    }

    @Entao("^deveria retornar a seguinte pauta consolidada$")
    public void deveriaRetornarAsSeguintesPautas(List<PautaConsolidadaDataTable> pautasConsolidadasDataTable) throws Exception {
        pautaVerificador.verificarConsolidado(pautasConsolidadasDataTable, retorno);
    }
}