package br.com.dbccompany.votacaocooperado.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "COOP_OWNER", name = "PAUTA")
@SequenceGenerator(schema = "COOP_OWNER", name = "pauta_seq", sequenceName = "PAUTA_SEQ", allocationSize = 1)
public class Pauta implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "pauta_seq")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "pauta", fetch = LAZY, cascade = ALL)
    @OrderBy("dataCriacao")
    public List<Assembleia> assembleias;

    public Date getDataCriacao() {
        return obterUltimaAssembleia().getDataCriacao();
    }

    public StatusAssembleia getStatusAssembleia() {
        return obterUltimaAssembleia().obterStatusAssembleia();
    }

    public int getQuantidadeVotosSim() {
        return obterUltimaAssembleia().obterQuantidadeVotosSim();
    }

    public int getQuantidadeVotosNao() {
        return obterUltimaAssembleia().obterQuantidadeVotosNao();
    }

    private Assembleia obterUltimaAssembleia() {
        return assembleias.stream()
                .reduce((primeiro, segundo) -> segundo)
                .orElse(Assembleia.builder().build());
    }
}