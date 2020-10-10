package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.repository.SessaoVotacaoRepository;
import br.com.dbccompany.votacaocooperado.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    public SessaoVotacaoServiceImpl(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    @Override
    public List<SessaoVotacao> listarTodos() {
        return sessaoVotacaoRepository.findAll();
    }

    @Override
    public SessaoVotacao cadastrar(SessaoVotacao sessaoVotacao) {
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }
}