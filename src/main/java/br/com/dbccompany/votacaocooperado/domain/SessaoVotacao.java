package br.com.dbccompany.votacaocooperado.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class SessaoVotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataCriacao;

    @Column(nullable = false)
    private int tempoDuracao;

    @ManyToOne
    private Pauta pauta;

    @OneToMany(mappedBy = "sessaoVotacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voto> votos;

    public SessaoVotacao() {
    }

    public SessaoVotacao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public Long obterIdPauta() {
        if (pauta == null) {
            return null;
        }
        return pauta.getId();
    }

    @PrePersist
    public void prePersist() {
        dataCriacao = new Date();
        if (tempoDuracao == 0) {
            tempoDuracao = 1;
        }
    }

    @Override
    public String toString() {
        return "SessaoVotacao{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", tempoDuracao=" + tempoDuracao +
                ", pauta=" + pauta +
                ", votos=" + votos +
                '}';
    }
}