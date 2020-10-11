package br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssembleiaRepositoryTestHelper extends JpaRepository<Assembleia, Long> {

    Assembleia findByPauta_Descricao(String descricao);

}