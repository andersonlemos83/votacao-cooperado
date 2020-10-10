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

    @ManyToOne
    private Associado associado;

    @ManyToOne
    private SessaoVotacao sessaoVotacao;

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

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }

    public Long obterIdAssociado() {
        if (associado == null) {
            return null;
        }
        return associado.getId();
    }

    public Long obterIdSessaoVotacao() {
        if (sessaoVotacao == null) {
            return null;
        }
        return sessaoVotacao.getId();
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", tipoVoto=" + tipoVoto +
                ", associado=" + associado +
                ", sessaoVotacao=" + sessaoVotacao +
                '}';
    }
}