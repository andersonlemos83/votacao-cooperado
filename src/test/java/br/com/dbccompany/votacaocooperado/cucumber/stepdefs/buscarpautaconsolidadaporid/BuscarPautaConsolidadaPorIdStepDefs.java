package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.buscarpautaconsolidadaporid;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaConsolidadaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.funcionalidade.PautaFuncionalidade;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.MensagemVerificador;
import br.com.dbccompany.votacaocooperado.cucumber.verificador.PautaVerificador;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@AllArgsConstructor
public class BuscarPautaConsolidadaPorIdStepDefs extends StepDefs {

    private final PautaFuncionalidade pautaFuncionalidade;
    private final PautaVerificador pautaVerificador;
    private final MensagemVerificador mensagemVerificador;

    @Quando("^buscar pauta consolidada por ID \"([^\"]*)\"$")
    public void buscarPautaConsolidadaPorId(Long id) throws Exception {
        ResultActions resultActions = pautaFuncionalidade.buscarPorId(id);
        transicaoDataTable.setResponse(resultActions);
    }

    @Entao("^deveria retornar a seguinte pauta consolidada$")
    public void deveriaRetornarAsSeguintesPautas(List<PautaConsolidadaDataTable> pautasConsolidadasDataTable) throws Exception {
        pautaVerificador.verificarConsolidado(pautasConsolidadasDataTable, transicaoDataTable.getResponse());
    }

    @Entao("^deveria retornar a mensagem \"([^\"]*)\"$")
    public void deveriaRetornarAhMensagem(String mensagem) throws Exception {
        mensagemVerificador.verificar(mensagem, transicaoDataTable.getResponse());
    }
}