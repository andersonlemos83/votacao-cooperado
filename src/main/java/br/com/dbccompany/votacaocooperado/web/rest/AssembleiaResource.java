package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assembleia")
@CrossOrigin(origins = "*")
public class AssembleiaResource {

    private static final Logger log = LoggerFactory.getLogger(AssembleiaResource.class);

    private final AssembleiaService assembleiaService;
    private final ModelMapper modelMapper;

    public AssembleiaResource(AssembleiaService assembleiaService,
                              ModelMapper modelMapper) {
        this.assembleiaService = assembleiaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<AssembleiaDto>> listarTodos() {
        log.info("Requisição Rest para listar todas as assembleias");
        List<Assembleia> assembleias = assembleiaService.listarTodos();
        List<AssembleiaDto> assembleiasDto = assembleias.stream()
                .map(assembleia -> modelMapper.map(assembleia, AssembleiaDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(assembleiasDto);
    }

    @PostMapping
    public ResponseEntity<AssembleiaDto> cadastrar(@Valid @RequestBody AssembleiaDto assembleiaDto) {
        log.info("Requisição Rest para cadastrar assembleia: {}", assembleiaDto);
        Assembleia assembleiaEntidade = modelMapper.map(assembleiaDto, Assembleia.class);
        Assembleia assembleiaCadastrada = assembleiaService.cadastrar(assembleiaEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(assembleiaCadastrada, AssembleiaDto.class));
    }
}