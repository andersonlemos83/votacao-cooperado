package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.builder.VotoBuilder;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.VotoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VotoContexto {

    @Autowired
    private VotoRepositoryTestHelper votoRepositoryTestHelper;

    @Autowired
    private AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    @Autowired
    private AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;

    public void cadastrar(List<VotoDataTable> votosDataTable) {
        votosDataTable.forEach(votoDataTable -> {
            Voto voto = converter(votoDataTable);
            votoRepositoryTestHelper.saveAndFlush(voto);
        });
    }

    private Voto converter(VotoDataTable votoDataTable) {
        Associado associado = associadoRepositoryTestHelper.findByNome(votoDataTable.getNomeAssociado());
        Assembleia assembleia = assembleiaRepositoryTestHelper.findByPauta_Descricao(votoDataTable.getDescricaoPauta());
        return VotoBuilder.umVoto()
                .comId(votoDataTable.getId())
                .comTipoVoto(votoDataTable.getTipoVoto())
                .comAssociado(associado)
                .comAssembleia(assembleia)
                .build();
    }
}