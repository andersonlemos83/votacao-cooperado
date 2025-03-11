package br.com.dbccompany.votacaocooperado.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class PautaDto implements Serializable {

    private static final String MENSAGEM_DESCRICAO_DA_PAUTA_E_OBRIGATORIA = "A descrição da pauta é obrigatória";

    private Long id;

    @NotEmpty(message = MENSAGEM_DESCRICAO_DA_PAUTA_E_OBRIGATORIA)
    @NotNull(message = MENSAGEM_DESCRICAO_DA_PAUTA_E_OBRIGATORIA)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "PautaDto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}