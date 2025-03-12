package br.com.dbccompany.votacaocooperado.cucumber.datatable.domain;

import br.com.dbccompany.votacaocooperado.domain.StatusAssembleia;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PautaConsolidadaDataTable implements Serializable {

    private String descricaoPauta;
    private StatusAssembleia statusAssembleia;
    private int quantidadeVotosSim;
    private int quantidadeVotosNao;

}