package br.com.dbccompany.votacaocooperado.web.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AssociadoDto implements Serializable {

    private Long id;

    @NotEmpty(message = "O nome do associado é obrigatório")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Associado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}