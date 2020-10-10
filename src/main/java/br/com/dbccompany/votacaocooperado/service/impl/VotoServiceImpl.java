package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.repository.VotoRepository;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public List<Voto> listarTodos() {
        return votoRepository.findAll();
    }

    @Override
    public Voto cadastrar(Voto voto) {
        return votoRepository.save(voto);
    }
}