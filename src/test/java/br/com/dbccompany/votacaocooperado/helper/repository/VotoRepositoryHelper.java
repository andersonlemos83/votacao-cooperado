package br.com.dbccompany.votacaocooperado.helper.repository;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositoryHelper extends JpaRepository<Voto, Long> {

    Voto findByAssociado_NomeAndAndAssembleia_Pauta_Descricao(String nomeAssociado, String descricaoPauta);

}