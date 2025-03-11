package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.contexto.AssembleiaContexto;
import br.com.dbccompany.votacaocooperado.cucumber.contexto.AssociadoContexto;
import br.com.dbccompany.votacaocooperado.cucumber.contexto.PautaContexto;
import br.com.dbccompany.votacaocooperado.cucumber.contexto.VotoContexto;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import io.cucumber.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContextoStepDefs extends StepDefs {

    @Autowired
    private PautaContexto pautaContexto;

    @Autowired
    private AssociadoContexto associadoContexto;

    @Autowired
    private AssembleiaContexto assembleiaContexto;

    @Autowired
    private VotoContexto votoContexto;

    @Dado("^que existam as pautas cadastradas$")
    public void queExistamAsPautasCadastradas(List<PautaDataTable> pautasDataTable) {
        pautaContexto.cadastrar(pautasDataTable);
    }

    @Dado("^que existam os associados cadastrados$")
    public void queExistamOsAssociadosCadastrados(List<AssociadoDataTable> associadosDataTable) {
        associadoContexto.cadastrar(associadosDataTable);
    }

    @Dado("^que existam as assembleias cadastradas$")
    public void queExistamAsAssembleiasCadastradas(List<AssembleiaDataTable> assembleiasDataTable) {
        assembleiaContexto.cadastrar(assembleiasDataTable);
    }

    @Dado("^que existam os votos cadastrados$")
    public void queExistamOsVotosCadastrados(List<VotoDataTable> votosDataTable) {
        votoContexto.cadastrar(votosDataTable);
    }
}