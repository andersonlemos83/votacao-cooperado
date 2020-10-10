package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.service.SessaoVotacaoService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorSessaoVotacao;
import br.com.dbccompany.votacaocooperado.web.dto.SessaoVotacaoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sessaoVotacao")
@CrossOrigin(origins = "*")
public class SessaoVotacaoResource {

    private static final Logger log = LoggerFactory.getLogger(SessaoVotacaoResource.class);

    private final SessaoVotacaoService sessaoVotacaoService;
    private final ConversorSessaoVotacao conversorSessaoVotacao;

    @Autowired
    public SessaoVotacaoResource(SessaoVotacaoService sessaoVotacaoService,
                                 ConversorSessaoVotacao conversorSessaoVotacao) {
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.conversorSessaoVotacao = conversorSessaoVotacao;
    }

    @GetMapping
    public ResponseEntity<List<SessaoVotacaoDto>> listarTodos() {
        log.info("Requisição Rest para listar todas as sessões de votação");
        List<SessaoVotacao> sessoes = sessaoVotacaoService.listarTodos();
        List<SessaoVotacaoDto> sessoesDto = conversorSessaoVotacao.converter(sessoes);
        return ResponseEntity.ok(sessoesDto);
    }

    @PostMapping
    public ResponseEntity<SessaoVotacaoDto> cadastrar(@Valid @RequestBody SessaoVotacaoDto sessaoVotacaoDto) {
        log.info("Requisição Rest para cadastrar sessão votação: {}", sessaoVotacaoDto);
        SessaoVotacao sessaoVotacaoEntidade = conversorSessaoVotacao.converter(sessaoVotacaoDto);
        SessaoVotacao sessaoVotacaoCadastrada = sessaoVotacaoService.cadastrar(sessaoVotacaoEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversorSessaoVotacao.converter(sessaoVotacaoCadastrada));
    }
}