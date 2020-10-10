package br.com.dbccompany.votacaocooperado.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PautaDto implements Serializable {

    private Long id;

    @NotEmpty(message = "A descrição da pauta é obrigatória")
    @NotNull(message = "A descrição da pauta é obrigatória")
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