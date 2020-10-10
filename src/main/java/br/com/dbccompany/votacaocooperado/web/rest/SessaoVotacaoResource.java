package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.SessaoVotacao;
import br.com.dbccompany.votacaocooperado.service.SessaoVotacaoService;
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

    @Autowired
    public SessaoVotacaoResource(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @GetMapping
    public ResponseEntity<List<SessaoVotacao>> listar() {
        log.info("Requisição Rest para listar todas as sessões de votação");
        List<SessaoVotacao> sessoes = sessaoVotacaoService.listarTodos();
        return ResponseEntity.ok(sessoes);
    }

    @PostMapping
    public ResponseEntity<SessaoVotacao> cadastrar(@Valid @RequestBody SessaoVotacao sessaoVotacao) {
        log.info("Requisição Rest para cadastrar sessão votação: {}", sessaoVotacao);
        SessaoVotacao sessaoVotacaoRetornada = sessaoVotacaoService.cadastrar(sessaoVotacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoVotacaoRetornada);
    }
}