package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorAssembleia;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assembleia")
@CrossOrigin(origins = "*")
public class AssembleiaResource {

    private static final Logger log = LoggerFactory.getLogger(AssembleiaResource.class);

    private final AssembleiaService assembleiaService;
    private final ConversorAssembleia conversorAssembleia;

    public AssembleiaResource(AssembleiaService assembleiaService,
                              ConversorAssembleia conversorAssembleia) {
        this.assembleiaService = assembleiaService;
        this.conversorAssembleia = conversorAssembleia;
    }

    @GetMapping
    public ResponseEntity<List<AssembleiaDto>> listarTodos() {
        log.info("Requisição Rest para listar todas as assembleias");
        List<Assembleia> assembleias = assembleiaService.listarTodos();
        List<AssembleiaDto> assembleiasDto = conversorAssembleia.converter(assembleias);
        return ResponseEntity.ok(assembleiasDto);
    }

    @PostMapping
    public ResponseEntity<AssembleiaDto> cadastrar(@Valid @RequestBody AssembleiaDto assembleiaDto) {
        log.info("Requisição Rest para cadastrar assembleia: {}", assembleiaDto);
        Assembleia assembleiaEntidade = conversorAssembleia.converter(assembleiaDto);
        Assembleia assembleiaCadastrada = assembleiaService.cadastrar(assembleiaEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversorAssembleia.converter(assembleiaCadastrada));
    }
}