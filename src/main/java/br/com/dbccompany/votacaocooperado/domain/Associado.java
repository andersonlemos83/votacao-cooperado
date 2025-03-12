package br.com.dbccompany.votacaocooperado.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(schema = "COOP_OWNER", name = "ASSOCIADO")
@SequenceGenerator(schema = "COOP_OWNER", name = "associado_seq", sequenceName = "ASSOCIADO_SEQ", allocationSize = 1)
public class Associado implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "associado_seq")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "associado", fetch = LAZY, cascade = ALL)
    private List<Voto> votos;

    public Associado() {
    }

    public Associado(Long id) {
        this.id = id;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associado associado = (Associado) o;
        return Objects.equals(id, associado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Associado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}