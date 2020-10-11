package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorVoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final ValidadorAssembleia validadorAssembleia;
    private final ValidadorVoto validadorVoto;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository,
                           ValidadorAssembleia validadorAssembleia,
                           ValidadorVoto validadorVoto) {
        this.votoRepository = votoRepository;
        this.validadorAssembleia = validadorAssembleia;
        this.validadorVoto = validadorVoto;
    }

    @Override
    public List<Voto> listarTodos() {
        return votoRepository.findAll();
    }

    @Override
    public Voto cadastrar(Voto voto) {
        validadorAssembleia.validar(voto.obterIdAssembleia());
        validadorVoto.validar(voto);
        return votoRepository.save(voto);
    }
}