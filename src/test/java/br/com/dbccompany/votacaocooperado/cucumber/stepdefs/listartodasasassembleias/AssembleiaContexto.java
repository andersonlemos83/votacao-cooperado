package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasasassembleias;

import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas.PautaRepositoryTestHelper;
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
        for (AssembleiaDataTable assembleiaDataTable : assembleiasDataTable) {
            Assembleia assembleia = converter(assembleiaDataTable);
            assembleiaRepositoryTestHelper.save(assembleia);
        }
    }

    private Assembleia converter(AssembleiaDataTable assembleiaDataTable) {
        Pauta pauta = pautaRepositoryTestHelper.findByDescricao(assembleiaDataTable.getDescricaoPauta());
        Assembleia assembleia = new Assembleia();
        assembleia.setId(assembleiaDataTable.getId());
        assembleia.setTempoDuracao(assembleiaDataTable.getTempoDuracao());
        assembleia.setPauta(pauta);
        return assembleia;
    }
}