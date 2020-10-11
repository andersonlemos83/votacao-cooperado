package br.com.dbccompany.votacaocooperado.repository;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {
}