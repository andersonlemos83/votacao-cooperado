package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.repository.PautaRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorAssembleiaImpl implements ValidadorAssembleia {

    private final AssembleiaRepository assembleiaRepository;
    private final PautaRepository pautaRepository;

    public ValidadorAssembleiaImpl(AssembleiaRepository assembleiaRepository,
                                   PautaRepository pautaRepository) {
        this.assembleiaRepository = assembleiaRepository;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public void validar(Long idAssembleia) {
        Optional<Assembleia> assembleiaOptional = assembleiaRepository.findById(idAssembleia);

        if (!assembleiaOptional.isPresent()) {
            throw new NegocioException("A assembleia informada não existe");
        }

        Assembleia assembleia = assembleiaOptional.get();
        if (assembleia.estaFechada()) {
            throw new NegocioException("A assembleia informada está fechada");
        }
    }

    @Override
    public void validar(Assembleia assembleia) {
        pautaRepository.findById(assembleia.obterIdPauta())
                .orElseThrow(() -> new NegocioException("A pauta informada não existe"));
    }
}