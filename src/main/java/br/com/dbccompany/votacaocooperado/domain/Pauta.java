package br.com.dbccompany.votacaocooperado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<SessaoVotacao> sessoesVotacao;

    public Pauta() {
    }

    public Pauta(Long id) {
        this.id = id;
    }

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

    public List<SessaoVotacao> getSessoesVotacao() {
        return sessoesVotacao;
    }

    public void setSessoesVotacao(List<SessaoVotacao> sessoesVotacao) {
        this.sessoesVotacao = sessoesVotacao;
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", sessoesVotacao=" + sessoesVotacao +
                '}';
    }
}