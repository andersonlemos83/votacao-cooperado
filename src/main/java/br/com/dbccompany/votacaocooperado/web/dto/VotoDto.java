package br.com.dbccompany.votacaocooperado.web.dto;

import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VotoDto implements Serializable {

    private Long id;

    @NotNull(message = "O tipo de voto é obrigatório")
    private TipoVoto tipoVoto;

    @NotNull(message = "O id do associado é obrigatório")
    private Long idAssociado;

    @NotNull(message = "O id da assembleia de votação é obrigatório")
    private Long idAssembleia;

}