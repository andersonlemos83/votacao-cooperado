package br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepositoryTestHelper extends JpaRepository<Pauta, Long> {

    Pauta findByDescricao(String descricao);

}