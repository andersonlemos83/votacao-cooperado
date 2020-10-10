package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorPauta;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
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
    private final ConversorPauta conversorPauta;

    @Autowired
    public PautaResource(PautaService pautaService,
                         ConversorPauta conversorPauta) {
        this.pautaService = pautaService;
        this.conversorPauta = conversorPauta;
    }

    @GetMapping
    public ResponseEntity<List<PautaDto>> listarTodos() {
        log.info("Requisição Rest para listar todas as pautas");
        List<Pauta> pautas = pautaService.listarTodos();
        List<PautaDto> pautasDto = conversorPauta.converter(pautas);
        return ResponseEntity.ok(pautasDto);
    }

    @PostMapping
    public ResponseEntity<PautaDto> cadastrar(@Valid @RequestBody PautaDto pautaDto) {
        log.info("Requisição Rest para cadastrar pauta: {}", pautaDto);
        Pauta pautaEntidade = conversorPauta.converter(pautaDto);
        Pauta pautaCadastrada = pautaService.cadastrar(pautaEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversorPauta.converter(pautaCadastrada));
    }
}