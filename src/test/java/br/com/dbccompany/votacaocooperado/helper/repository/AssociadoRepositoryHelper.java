package br.com.dbccompany.votacaocooperado.helper.repository;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepositoryHelper extends JpaRepository<Associado, Long> {

    Associado findByNome(String nome);

    Associado findByCpf(String cpf);

}