package br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositoryTestHelper extends JpaRepository<Voto, Long> {
}
