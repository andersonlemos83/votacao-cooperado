package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssociado;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
public class ConversorAssociadoImpl implements ConversorAssociado {
    @Override
    public Associado converter(AssociadoDto associadoDto) {
        Associado associado = new Associado();
        associado.setId(associadoDto.getId());
        associado.setNome(associadoDto.getNome());
        associado.setCpf(associadoDto.getCpf());
        return associado;
    }

    @Override
    public List<AssociadoDto> converter(List<Associado> associados) {
        List<AssociadoDto> associadosDto = new ArrayList<>();
        for (Associado associado : Optional.ofNullable(associados).orElse(emptyList())) {
            associadosDto.add(converter(associado));
        }
        return associadosDto;
    }

    @Override
    public AssociadoDto converter(Associado associado) {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setId(associado.getId());
        associadoDto.setNome(associado.getNome());
        associadoDto.setCpf(associado.getCpf());
        return associadoDto;
    }
}