package br.com.dbccompany.votacaocooperado.web.conversor;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.web.dto.SessaoVotacaoDto;

import javax.validation.Valid;
import java.util.List;

public interface ConversorSessaoVotacao {

    SessaoVotacao converter(@Valid SessaoVotacaoDto sessaoVotacaoDto);

    List<SessaoVotacaoDto> converter(List<SessaoVotacao> sessoesVotacao);

    SessaoVotacaoDto converter(SessaoVotacao sessaoVotacao);
}