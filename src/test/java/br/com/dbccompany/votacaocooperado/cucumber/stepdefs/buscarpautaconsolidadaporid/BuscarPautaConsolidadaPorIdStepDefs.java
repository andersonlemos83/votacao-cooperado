package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.buscarpautaconsolidadaporid;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaConsolidadaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.MensagemVerificador;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BuscarPautaConsolidadaPorIdStepDefs extends StepDefs {

    @Autowired
    private PautaFuncionalidade pautaFuncionalidade;

    @Autowired
    private PautaVerificador pautaVerificador;

    @Autowired
    private MensagemVerificador mensagemVerificador;

    @Before
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @Quando("^buscar pauta consolidada por ID \"([^\"]*)\"$")
    public void buscarPautaConsolidadaPorId(Long id) throws Exception {
        retorno = pautaFuncionalidade.buscarPorId(id);
    }

    @Entao("^deveria retornar a seguinte pauta consolidada$")
    public void deveriaRetornarAsSeguintesPautas(List<PautaConsolidadaDataTable> pautasConsolidadasDataTable) throws Exception {
        pautaVerificador.verificarConsolidado(pautasConsolidadasDataTable, retorno);
    }

    @Entao("^deveria retornar a mensagem \"([^\"]*)\"$")
    public void deveriaRetornarAhMensagem(String mensagem) throws Exception {
        mensagemVerificador.verificar(mensagem, retorno);
    }
}