package br.com.dbccompany.votacaocooperado.repository;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}