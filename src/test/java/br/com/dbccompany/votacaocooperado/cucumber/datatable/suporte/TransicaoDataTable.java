package br.com.dbccompany.votacaocooperado.cucumber.datatable.suporte;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssembleiaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.PautaDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.VotoDataTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransicaoDataTable implements Serializable {

    private ResultActions response;

    @Builder.Default
    private VotoDataTable votoDataTable = VotoDataTable.builder().build();
    @Builder.Default
    private PautaDataTable pautaDataTable = PautaDataTable.builder().build();
    @Builder.Default
    private AssociadoDataTable associadoDataTable = AssociadoDataTable.builder().build();
    @Builder.Default
    private AssembleiaDataTable assembleiaDataTable = AssembleiaDataTable.builder().build();

}