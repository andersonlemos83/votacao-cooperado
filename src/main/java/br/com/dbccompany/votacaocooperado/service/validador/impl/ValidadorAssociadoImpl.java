package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.CpfRepository;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorAssociadoImpl implements ValidadorAssociado {

    private final CpfRepository cpfRepository;

    @Autowired
    public ValidadorAssociadoImpl(CpfRepository cpfRepository) {
        this.cpfRepository = cpfRepository;
    }

    @Override
    public void validar(Associado associado) {
        boolean cpfValido = cpfRepository.verificarSeEstaValido(associado.getCpf());
        if (!cpfValido) {
            throw new NegocioException("O CPF do associado é inválido");
        }
    }
}