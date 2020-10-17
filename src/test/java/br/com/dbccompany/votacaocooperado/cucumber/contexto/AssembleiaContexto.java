package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.builder.AssembleiaBuilder;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssembleiaContexto {

    @Autowired
    private AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void cadastrar(List<AssembleiaDataTable> assembleiasDataTable) {
        assembleiasDataTable.forEach(assembleiaDataTable -> {
            Assembleia assembleia = converter(assembleiaDataTable);
            assembleiaRepositoryTestHelper.saveAndFlush(assembleia);
        });
    }

    private Assembleia converter(AssembleiaDataTable assembleiaDataTable) {
        Pauta pauta = pautaRepositoryTestHelper.findByDescricao(assembleiaDataTable.getDescricaoPauta());
        return AssembleiaBuilder.umaAssembleia()
                .comId(assembleiaDataTable.getId())
                .comTempoDuracao(assembleiaDataTable.getTempoDuracao())
                .comDataCriacao(assembleiaDataTable.obterDataCriacao())
                .comPauta(pauta)
                .build();
    }
}