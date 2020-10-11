package br.com.dbccompany.votacaocooperado.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("dataCriacao")
    public List<Assembleia> assembleias;

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

    public List<Assembleia> getAssembleias() {
        return assembleias;
    }

    public void setAssembleias(List<Assembleia> assembleias) {
        this.assembleias = assembleias;
    }

    public Assembleia obterUltimaAssembleia() {
        return assembleias.stream().reduce((primeiro, segundo) -> segundo).orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}