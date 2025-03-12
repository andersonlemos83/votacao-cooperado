package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.AssociadoRepository;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    private final ValidadorAssociado validadorAssociado;
    private final AssociadoRepository associadoRepository;

    @Override
    public List<Associado> listarTodos() {
        return associadoRepository.findAll();
    }

    @Override
    public Associado cadastrar(Associado associado) {
        validadorAssociado.validar(associado);
        return associadoRepository.save(associado);
    }
}