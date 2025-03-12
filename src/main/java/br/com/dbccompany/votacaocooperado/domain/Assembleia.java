package br.com.dbccompany.votacaocooperado.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;
import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.FECHADA;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;
import static java.time.ZoneId.systemDefault;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@SuppressWarnings("squid:S7091") // Circular dependencies between classes across packages
@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "COOP_OWNER", name = "ASSEMBLEIA")
@SequenceGenerator(schema = "COOP_OWNER", name = "assembleia_seq", sequenceName = "ASSEMBLEIA_SEQ", allocationSize = 1)
public class Assembleia implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "assembleia_seq")
    private Long id;

    @Column(nullable = false)
    private Date dataCriacao;

    @Column(nullable = false)
    private int tempoDuracao;

    @ManyToOne
    private Pauta pauta;

    @OneToMany(mappedBy = "assembleia", fetch = LAZY, cascade = ALL)
    private List<Voto> votos;

    public Long obterIdPauta() {
        return Optional.ofNullable(pauta)
                .map(Pauta::getId)
                .orElse(null);
    }

    public boolean estaFechada() {
        if (dataCriacao == null) {
            return true;
        }
        return dataCriacao.toInstant()
                .atZone(systemDefault())
                .toLocalDateTime()
                .plusMinutes(tempoDuracao)
                .isBefore(now());
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
}