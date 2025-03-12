package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AssembleiaContexto {

    private final AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;
    private final PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void cadastrar(List<AssembleiaDataTable> assembleiasDataTable) {
        List<Assembleia> assembleias = assembleiasDataTable.stream().map(this::gerarAssembleia).toList();
        assembleiaRepositoryTestHelper.saveAll(assembleias);
    }

    private Assembleia gerarAssembleia(AssembleiaDataTable assembleiaDataTable) {
        Pauta pauta = pautaRepositoryTestHelper.findByDescricao(assembleiaDataTable.getDescricaoPauta());
        Assembleia assembleia = new Assembleia();
        BeanUtils.copyProperties(assembleiaDataTable, assembleia);
        assembleia.setId(null);
        assembleia.setPauta(pauta);
        assembleia.setDataCriacao(assembleiaDataTable.obterDataCriacao());
        return assembleia;
    }
}