package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorVoto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/voto")
@CrossOrigin(origins = "*")
public class VotoResource {

    private static final Logger log = LoggerFactory.getLogger(VotoResource.class);

    private final VotoService votoService;
    private final ConversorVoto conversorVoto;

    public VotoResource(VotoService votoService,
                        ConversorVoto conversorVoto) {
        this.votoService = votoService;
        this.conversorVoto = conversorVoto;
    }

    @GetMapping
    public ResponseEntity<List<VotoDto>> listar() {
        log.info("Requisição Rest para listar todos os votos");
        List<Voto> votos = votoService.listarTodos();
        List<VotoDto> votosDto = conversorVoto.converter(votos);
        return ResponseEntity.ok(votosDto);
    }

    @PostMapping
    public ResponseEntity<VotoDto> cadastrar(@Valid @RequestBody VotoDto VotoDto) {
        log.info("Requisição Rest para cadastrar voto: {}", VotoDto);
        Voto votoEntidade = conversorVoto.converter(VotoDto);
        Voto votoCadastrado = votoService.cadastrar(votoEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversorVoto.converter(votoCadastrado));
    }
}