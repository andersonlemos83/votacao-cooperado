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
public class AssociadoDto implements Serializable {

    private static final String MENSAGEM_O_NOME_DO_ASSOCIADO_E_OBRIGATORIO = "O nome do associado é obrigatório";
    private static final String MENSAGEM_O_CPF_DO_ASSOCIADO_E_OBRIGATORIO = "O CPF do associado é obrigatório";

    private Long id;

    @NotEmpty(message = MENSAGEM_O_NOME_DO_ASSOCIADO_E_OBRIGATORIO)
    @NotNull(message = MENSAGEM_O_NOME_DO_ASSOCIADO_E_OBRIGATORIO)
    private String nome;

    @NotEmpty(message = MENSAGEM_O_CPF_DO_ASSOCIADO_E_OBRIGATORIO)
    @NotNull(message = MENSAGEM_O_CPF_DO_ASSOCIADO_E_OBRIGATORIO)
    private String cpf;

}