package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidadorVotoImpl implements ValidadorVoto {

    private final VotoRepository votoRepository;

    @Override
    public void validar(Voto voto) {
        Optional<Voto> votoOptinal = buscarVoto(voto);
        votoOptinal.ifPresent(v -> {
            throw new NegocioException("O associado já exerceu seu direito de voto para esta pauta");
        });
    }

    private Optional<Voto> buscarVoto(Voto voto) {
        return Optional.ofNullable(votoRepository.findByAssociado_IdAndAssembleia_Id(voto.obterIdAssociado(), voto.obterIdAssembleia()));
    }
}