package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.VotoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VotoContexto {

    private final VotoRepositoryTestHelper votoRepositoryTestHelper;
    private final AssociadoRepositoryTestHelper associadoRepositoryTestHelper;
    private final AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;

    public void cadastrar(List<VotoDataTable> votosDataTable) {
        List<Voto> votos = votosDataTable.stream().map(this::gerarVoto).toList();
        votoRepositoryTestHelper.saveAll(votos);
    }

    private Voto gerarVoto(VotoDataTable votoDataTable) {
        Associado associado = associadoRepositoryTestHelper.findByNome(votoDataTable.getNomeAssociado());
        Assembleia assembleia = assembleiaRepositoryTestHelper.findByPauta_Descricao(votoDataTable.getDescricaoPauta());
        Voto voto = new Voto();
        BeanUtils.copyProperties(votoDataTable, voto);
        voto.setId(null);
        voto.setAssociado(associado);
        voto.setAssembleia(assembleia);
        return voto;
    }
}