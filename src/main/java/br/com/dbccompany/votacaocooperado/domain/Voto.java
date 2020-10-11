package br.com.dbccompany.votacaocooperado.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVoto tipoVoto;

    @ManyToOne
    private Associado associado;

    @ManyToOne
    private Assembleia assembleia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVoto getTipoVoto() {
        return tipoVoto;
    }

    public void setTipoVoto(TipoVoto tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Assembleia getAssembleia() {
        return assembleia;
    }

    public void setAssembleia(Assembleia assembleia) {
        this.assembleia = assembleia;
    }

    public Long obterIdAssociado() {
        if (associado == null) {
            return null;
        }
        return associado.getId();
    }

    public Long obterIdAssembleia() {
        if (assembleia == null) {
            return null;
        }
        return assembleia.getId();
    }

    public boolean ehSim() {
        return tipoVoto != null && tipoVoto == TipoVoto.SIM;
    }

    public boolean ehNao() {
        return tipoVoto != null && tipoVoto == TipoVoto.NAO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", tipoVoto=" + tipoVoto +
                ", associado=" + associado +
                ", assembleia=" + assembleia +
                '}';
    }
}