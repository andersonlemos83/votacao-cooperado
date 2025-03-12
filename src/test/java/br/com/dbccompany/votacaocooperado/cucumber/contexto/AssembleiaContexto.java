package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.helper.repository.AssembleiaRepositoryHelper;
import br.com.dbccompany.votacaocooperado.helper.repository.PautaRepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AssembleiaContexto {

    private final AssembleiaRepositoryHelper assembleiaRepositoryHelper;
    private final PautaRepositoryHelper pautaRepositoryHelper;

    public void cadastrar(List<AssembleiaDataTable> assembleiasDataTable) {
        List<Assembleia> assembleias = assembleiasDataTable.stream().map(this::gerarAssembleia).toList();
        assembleiaRepositoryHelper.saveAll(assembleias);
    }

    private Assembleia gerarAssembleia(AssembleiaDataTable assembleiaDataTable) {
        Pauta pauta = pautaRepositoryHelper.findByDescricao(assembleiaDataTable.getDescricaoPauta());
        Assembleia assembleia = new Assembleia();
        BeanUtils.copyProperties(assembleiaDataTable, assembleia);
        assembleia.setId(null);
        assembleia.setPauta(pauta);
        assembleia.setDataCriacao(assembleiaDataTable.obterDataCriacao());
        return assembleia;
    }
}