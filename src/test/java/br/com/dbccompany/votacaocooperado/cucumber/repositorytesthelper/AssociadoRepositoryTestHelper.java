package br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepositoryTestHelper extends JpaRepository<Associado, Long> {

    Associado findByNome(String nome);

}
