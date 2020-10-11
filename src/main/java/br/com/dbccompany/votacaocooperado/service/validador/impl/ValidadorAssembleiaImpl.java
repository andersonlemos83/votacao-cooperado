package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorAssembleiaImpl implements ValidadorAssembleia {

    private final AssembleiaRepository assembleiaRepository;

    @Autowired
    public ValidadorAssembleiaImpl(AssembleiaRepository assembleiaRepository) {
        this.assembleiaRepository = assembleiaRepository;
    }

    @Override
    public void validar(Long idAssembleia) {
        Optional<Assembleia> assembleiaOptional = assembleiaRepository.findById(idAssembleia);

        if (!assembleiaOptional.isPresent()) {
            throw new NegocioException("A assembleia de votação informada não existe");
        }

        Assembleia assembleia = assembleiaOptional.get();
        if (assembleia.estaFechada()) {
            throw new NegocioException("A assembleia de votação informada está fechada");
        }
    }
}
