package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.repository.AssembleiaRepository;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssembleia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssembleiaServiceImpl implements AssembleiaService {

    private final ValidadorAssembleia validadorAssembleia;
    private final AssembleiaRepository assembleiaRepository;

    public AssembleiaServiceImpl(ValidadorAssembleia validadorAssembleia,
                                 AssembleiaRepository assembleiaRepository) {
        this.validadorAssembleia = validadorAssembleia;
        this.assembleiaRepository = assembleiaRepository;
    }

    @Override
    public List<Assembleia> listarTodos() {
        return assembleiaRepository.findAll();
    }

    @Override
    public Assembleia cadastrar(Assembleia assembleia) {
        validadorAssembleia.validar(assembleia);
        return assembleiaRepository.save(assembleia);
    }
}