package br.com.dbccompany.votacaocooperado.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;
import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.FECHADA;
import static java.lang.Boolean.TRUE;
import static java.time.ZoneId.systemDefault;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Entity
public class Assembleia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataCriacao;

    @Column(nullable = false)
    private int tempoDuracao;

    @ManyToOne
    private Pauta pauta;

    @OneToMany(mappedBy = "assembleia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voto> votos;

    public Assembleia() {
    }

    public Assembleia(Long id) {
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

    public boolean estaFechada() {
        if (dataCriacao == null) {
            return true;
        }
        return dataCriacao.toInstant()
                .atZone(systemDefault())
                .toLocalDateTime()
                .plusMinutes(tempoDuracao)
                .isBefore(LocalDateTime.now());
    }

    public int obterQuantidadeVotosSim() {
        Map<Boolean, Long> apuracao = Optional.ofNullable(votos)
                .orElse(emptyList())
                .stream()
                .collect(groupingBy(Voto::ehSim, counting()));
        return apuracao.getOrDefault(TRUE, 0L).intValue();
    }

    public int obterQuantidadeVotosNao() {
        Map<Boolean, Long> apuracao = Optional.ofNullable(votos)
                .orElse(emptyList())
                .stream()
                .collect(groupingBy(Voto::ehNao, counting()));
        return apuracao.getOrDefault(TRUE, 0L).intValue();
    }

    public StatusAssembleia obterStatusAssembleia() {
        if (dataCriacao == null || estaFechada()) {
            return FECHADA;
        }
        return ABERTA;
    }

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            dataCriacao = new Date();
        }

        if (tempoDuracao <= 0) {
            tempoDuracao = 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assembleia that = (Assembleia) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Assembleia{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", tempoDuracao=" + tempoDuracao +
                ", pauta=" + pauta +
                '}';
    }
}