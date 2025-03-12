package br.com.dbccompany.votacaocooperado.cucumber.datatable;

import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VotoDataTable implements Serializable {

    private Long id;
    private TipoVoto tipoVoto;
    private String nomeAssociado;
    private Long idAssociado;
    private String descricaoPauta;
    private Long idAssembleia;

}