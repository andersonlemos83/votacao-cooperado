package br.com.dbccompany.votacaocooperado.web.conversor.impl;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorSessaoVotacao;
import br.com.dbccompany.votacaocooperado.web.dto.SessaoVotacaoDto;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConversorSessaoVotacaoImpl implements ConversorSessaoVotacao {
    @Override
    public SessaoVotacao converter(@Valid SessaoVotacaoDto sessaoVotacaoDto) {
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setId(sessaoVotacaoDto.getId());
        sessaoVotacao.setTempoDuracao(sessaoVotacaoDto.getTempoDuracao());
        sessaoVotacao.setPauta(new Pauta(sessaoVotacaoDto.getIdPauta()));
        return sessaoVotacao;
    }

    @Override
    public List<SessaoVotacaoDto> converter(List<SessaoVotacao> sessoesVotacao) {
        List<SessaoVotacaoDto> sessoesVotacaoDto = new ArrayList<>();
        for (SessaoVotacao sessaoVotacao : sessoesVotacao) {
            sessoesVotacaoDto.add(converter(sessaoVotacao));
        }
        return sessoesVotacaoDto;
    }

    @Override
    public SessaoVotacaoDto converter(SessaoVotacao sessaoVotacao) {
        SessaoVotacaoDto sessaoVotacaoDto = new SessaoVotacaoDto();
        sessaoVotacaoDto.setId(sessaoVotacao.getId());
        sessaoVotacaoDto.setDataCriacao(sessaoVotacao.getDataCriacao());
        sessaoVotacaoDto.setTempoDuracao(sessaoVotacao.getTempoDuracao());
        sessaoVotacaoDto.setIdPauta(sessaoVotacao.obterIdPauta());
        return sessaoVotacaoDto;
    }
}
