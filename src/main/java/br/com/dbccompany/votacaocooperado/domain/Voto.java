package br.com.dbccompany.votacaocooperado.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVoto tipoVoto;

    @Column(nullable = false)
    @Transient
    private Associado associado;

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

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", tipoVoto=" + tipoVoto +
                ", associado=" + associado +
                '}';
    }
}