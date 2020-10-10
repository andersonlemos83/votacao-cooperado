package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssociado;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConversorAssociadoImpl implements ConversorAssociado {
    @Override
    public Associado converter(@Valid AssociadoDto associadoDto) {
        Associado associado = new Associado();
        associado.setId(associadoDto.getId());
        associado.setNome(associadoDto.getNome());
        return associado;
    }

    @Override
    public List<AssociadoDto> converter(List<Associado> associados) {
        List<AssociadoDto> associadosDto = new ArrayList<>();
        for (Associado associado : associados) {
            associadosDto.add(converter(associado));
        }
        return associadosDto;
    }

    @Override
    public AssociadoDto converter(Associado associado) {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setId(associado.getId());
        associadoDto.setNome(associado.getNome());
        return associadoDto;
    }
}