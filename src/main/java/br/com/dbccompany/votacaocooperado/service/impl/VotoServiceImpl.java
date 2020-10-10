package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorSessaoVotacao;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final ValidadorSessaoVotacao validadorSessaoVotacao;
    private final ValidadorVoto validadorVoto;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository,
                           ValidadorSessaoVotacao validadorSessaoVotacao,
                           ValidadorVoto validadorVoto) {
        this.votoRepository = votoRepository;
        this.validadorSessaoVotacao = validadorSessaoVotacao;
        this.validadorVoto = validadorVoto;
    }

    @Override
    public List<Voto> listarTodos() {
        return votoRepository.findAll();
    }

    @Override
    public Voto cadastrar(Voto voto) {
        validadorSessaoVotacao.validar(voto.obterIdSessaoVotacao());
        validadorVoto.validar(voto);
        return votoRepository.save(voto);
    }
}