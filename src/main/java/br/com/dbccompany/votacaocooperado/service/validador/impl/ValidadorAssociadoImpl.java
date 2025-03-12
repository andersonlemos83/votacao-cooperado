package br.com.dbccompany.votacaocooperado.service.validador.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.repository.AssociadoRepository;
import br.com.dbccompany.votacaocooperado.client.UsuarioClient;
import br.com.dbccompany.votacaocooperado.service.validador.ValidadorAssociado;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidadorAssociadoImpl implements ValidadorAssociado {

    private final UsuarioClient usuarioClient;
    private final AssociadoRepository associadoRepository;

    public ValidadorAssociadoImpl(UsuarioClient usuarioClient,
                                  AssociadoRepository associadoRepository) {
        this.usuarioClient = usuarioClient;
        this.associadoRepository = associadoRepository;
    }

    @Override
    public void validar(Associado associado) {
        boolean cpfValido = usuarioClient.verificarSeEstaValido(associado.getCpf());
        if (!cpfValido) {
            throw new NegocioException("O CPF do associado é inválido");
        }

        Optional<Associado> associadoOptional = associadoRepository.findByCpf(associado.getCpf());
        associadoOptional.ifPresent(a -> {
            throw new NegocioException("O CPF informado já está cadastrado");
        });
    }
}