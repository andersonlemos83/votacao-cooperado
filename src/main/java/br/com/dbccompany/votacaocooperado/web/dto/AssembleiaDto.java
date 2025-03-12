package br.com.dbccompany.votacaocooperado.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssembleiaDto implements Serializable {

    private Long id;
    @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Maceio")
    private Date dataCriacao;
    private int tempoDuracao;
    @NotNull(message = "O ID da pauta é obrigatório")
    private Long idPauta;
    private List<VotoDto> votos;

}