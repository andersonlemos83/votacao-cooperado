package br.com.dbccompany.votacaocooperado.repository;

import br.com.dbccompany.votacaocooperado.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
}
