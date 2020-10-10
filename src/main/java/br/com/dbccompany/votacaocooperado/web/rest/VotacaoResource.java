package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Votacao;
import br.com.dbccompany.votacaocooperado.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/votacao")
@CrossOrigin(origins = "*")
public class VotacaoResource {

    private static final Logger log = Logger.getLogger(VotacaoResource.class.getName());

    @Autowired
    private VotacaoService votacaoService;

    @GetMapping
    public ResponseEntity<List<Votacao>> listar() {
        log.info("Requisição Rest para listar todos");
        return ResponseEntity.ok(votacaoService.listarTodos());
    }
}