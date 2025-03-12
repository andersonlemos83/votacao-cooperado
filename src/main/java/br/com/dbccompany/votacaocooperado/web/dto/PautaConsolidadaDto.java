package br.com.dbccompany.votacaocooperado.web.dto;

import br.com.dbccompany.votacaocooperado.domain.StatusAssembleia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PautaConsolidadaDto {

    private String descricao;
    @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Maceio")
    private Date dataCriacao;
    private StatusAssembleia statusAssembleia;
    private int quantidadeVotosSim;
    private int quantidadeVotosNao;

}