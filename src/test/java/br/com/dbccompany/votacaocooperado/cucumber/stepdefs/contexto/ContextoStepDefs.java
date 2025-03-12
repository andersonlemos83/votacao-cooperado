package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.contexto.AssembleiaContexto;
import br.com.dbccompany.votacaocooperado.cucumber.contexto.AssociadoContexto;
import br.com.dbccompany.votacaocooperado.cucumber.contexto.PautaContexto;
import br.com.dbccompany.votacaocooperado.cucumber.contexto.VotoContexto;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import io.cucumber.java.pt.Dado;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ContextoStepDefs extends StepDefs {

    private final PautaContexto pautaContexto;
    private final AssociadoContexto associadoContexto;
    private final AssembleiaContexto assembleiaContexto;
    private final VotoContexto votoContexto;

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