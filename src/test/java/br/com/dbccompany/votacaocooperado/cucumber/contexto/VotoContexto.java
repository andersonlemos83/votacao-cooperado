package br.com.dbccompany.votacaocooperado.cucumber.contexto;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.VotoDataTable;
import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.helper.repository.AssembleiaRepositoryHelper;
import br.com.dbccompany.votacaocooperado.helper.repository.AssociadoRepositoryHelper;
import br.com.dbccompany.votacaocooperado.helper.repository.VotoRepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VotoContexto {

    private final VotoRepositoryHelper votoRepositoryHelper;
    private final AssociadoRepositoryHelper associadoRepositoryHelper;
    private final AssembleiaRepositoryHelper assembleiaRepositoryHelper;

    public void cadastrar(List<VotoDataTable> votosDataTable) {
        List<Voto> votos = votosDataTable.stream().map(this::gerarVoto).toList();
        votoRepositoryHelper.saveAll(votos);
    }

    private Voto gerarVoto(VotoDataTable votoDataTable) {
        Associado associado = associadoRepositoryHelper.findByNome(votoDataTable.getNomeAssociado());
        Assembleia assembleia = assembleiaRepositoryHelper.findByPauta_Descricao(votoDataTable.getDescricaoPauta());
        Voto voto = new Voto();
        BeanUtils.copyProperties(votoDataTable, voto);
        voto.setId(null);
        voto.setAssociado(associado);
        voto.setAssembleia(assembleia);
        return voto;
    }
}