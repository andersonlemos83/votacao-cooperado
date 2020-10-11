package br.com.dbccompany.votacaocooperado.repository;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Voto findByAssociado_IdAndAssembleia_Id(Long idAssociado, Long idAssembleia);

}