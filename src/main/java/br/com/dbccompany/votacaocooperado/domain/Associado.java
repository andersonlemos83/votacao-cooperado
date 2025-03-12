package br.com.dbccompany.votacaocooperado.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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

}