package br.com.dbccompany.votacaocooperado.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Optional;

import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.NAO;
import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.SIM;
import static jakarta.persistence.GenerationType.SEQUENCE;

@SuppressWarnings("squid:S7091") // Circular dependencies between classes across packages
@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "COOP_OWNER", name = "VOTO")
@SequenceGenerator(schema = "COOP_OWNER", name = "voto_seq", sequenceName = "VOTO_SEQ", allocationSize = 1)
public class Voto implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "voto_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVoto tipoVoto;

    @ManyToOne
    private Associado associado;

    @ManyToOne
    private Assembleia assembleia;

    public Long obterIdAssociado() {
        return Optional.ofNullable(associado)
                .map(Associado::getId)
                .orElse(null);
    }

    public Long obterIdAssembleia() {
        return Optional.ofNullable(assembleia)
                .map(Assembleia::getId)
                .orElse(null);
    }

    public boolean ehSim() {
        return SIM.equals(tipoVoto);
    }

    public boolean ehNao() {
        return NAO.equals(tipoVoto);
    }
}