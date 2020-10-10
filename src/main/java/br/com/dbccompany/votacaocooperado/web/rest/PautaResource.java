package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pauta")
@CrossOrigin(origins = "*")
public class PautaResource {

    private static final Logger log = LoggerFactory.getLogger(PautaResource.class);

    private final PautaService pautaService;

    @Autowired
    public PautaResource(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> listar() {
        log.info("Requisição Rest para listar todas as pautas");
        List<Pauta> pautas = pautaService.listarTodos();
        return ResponseEntity.ok(pautas);
    }

    @PostMapping
    public ResponseEntity<Pauta> cadastrar(@Valid @RequestBody Pauta pauta) {
        log.info("Requisição Rest para cadastrar pauta: {}", pauta);
        Pauta pautaRetornada = pautaService.cadastrar(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaRetornada);
    }
}