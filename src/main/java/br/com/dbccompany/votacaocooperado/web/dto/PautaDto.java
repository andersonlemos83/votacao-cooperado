package br.com.dbccompany.votacaocooperado.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PautaDto implements Serializable {

    private static final String MENSAGEM_DESCRICAO_DA_PAUTA_E_OBRIGATORIA = "A descrição da pauta é obrigatória";

    private Long id;

    @NotEmpty(message = MENSAGEM_DESCRICAO_DA_PAUTA_E_OBRIGATORIA)
    @NotNull(message = MENSAGEM_DESCRICAO_DA_PAUTA_E_OBRIGATORIA)
    private String descricao;

}