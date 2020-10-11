package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.repository.SessaoVotacaoRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorSessaoVotacao;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorSessaoVotacaoImpl implements ValidadorSessaoVotacao {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    public ValidadorSessaoVotacaoImpl(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    @Override
    public void validar(Long idSessaoVotacao) {
        Optional<SessaoVotacao> sessaoVotacaoOptional = sessaoVotacaoRepository.findById(idSessaoVotacao);

        if (!sessaoVotacaoOptional.isPresent()) {
            throw new NegocioException("A sessão de votação informada não existe");
        }

        SessaoVotacao sessaoVotacao = sessaoVotacaoOptional.get();
        if (sessaoVotacao.estaFechada()) {
            throw new NegocioException("A sessão de votação informada está fechada");
        }
    }
}
