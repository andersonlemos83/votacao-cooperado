package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/associados")
@CrossOrigin(origins = "*")
public class AssociadoResource {

    private static final Logger log = LoggerFactory.getLogger(AssociadoResource.class);

    private final AssociadoService associadoService;
    private final ModelMapper modelMapper;

    public AssociadoResource(AssociadoService associadoService,
                             ModelMapper modelMapper) {
        this.associadoService = associadoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<AssociadoDto>> listar() {
        log.info("Requisição Rest para listar todos os associados");
        List<Associado> associados = associadoService.listarTodos();
        List<AssociadoDto> associadosDto = associados.stream()
                .map(associado -> modelMapper.map(associado, AssociadoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(associadosDto);
    }

    @PostMapping
    public ResponseEntity<AssociadoDto> cadastrar(@Valid @RequestBody AssociadoDto associadoDto) {
        log.info("Requisição Rest para cadastrar associado: {}", associadoDto);
        Associado associadoEntidade = modelMapper.map(associadoDto, Associado.class);
        Associado associadoCadastrado = associadoService.cadastrar(associadoEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(associadoCadastrado, AssociadoDto.class));
    }
}