package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssociado;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/associado")
@CrossOrigin(origins = "*")
public class AssociadoResource {

    private static final Logger log = LoggerFactory.getLogger(AssociadoResource.class);

    private final AssociadoService associadoService;
    private final ConversorAssociado conversorAssociado;

    @Autowired
    public AssociadoResource(AssociadoService associadoService,
                             ConversorAssociado conversorAssociado) {
        this.associadoService = associadoService;
        this.conversorAssociado = conversorAssociado;
    }

    @GetMapping
    public ResponseEntity<List<AssociadoDto>> listar() {
        log.info("Requisição Rest para listar todas os associados");
        List<Associado> associados = associadoService.listarTodos();
        List<AssociadoDto> associadosDto = conversorAssociado.converter(associados);
        return ResponseEntity.ok(associadosDto);
    }

    @PostMapping
    public ResponseEntity<AssociadoDto> cadastrar(@Valid @RequestBody AssociadoDto associadoDto) {
        log.info("Requisição Rest para cadastrar associado: {}", associadoDto);
        Associado associadoEntidade = conversorAssociado.converter(associadoDto);
        Associado associadoCadastrado = associadoService.cadastrar(associadoEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversorAssociado.converter(associadoCadastrado));
    }
}