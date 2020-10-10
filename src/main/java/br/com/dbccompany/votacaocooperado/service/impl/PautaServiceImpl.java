package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.repository.PautaRepository;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public List<Pauta> listarTodos() {
        return pautaRepository.findAll();
    }

    @Override
    public Pauta cadastrar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}