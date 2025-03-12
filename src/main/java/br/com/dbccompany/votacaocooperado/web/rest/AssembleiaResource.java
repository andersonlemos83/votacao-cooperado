package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.service.AssembleiaService;
import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.dbccompany.votacaocooperado.shared.util.ObjectMapperUtil.generateJson;
import static org.springframework.http.HttpStatus.CREATED;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/assembleias")
public class AssembleiaResource {

    private final AssembleiaService assembleiaService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AssembleiaDto>> listarTodos() {
        log.info("---> Request GET /v1/api/assembleias");
        List<Assembleia> assembleias = assembleiaService.listarTodos();
        List<AssembleiaDto> assembleiasDto = assembleias.stream()
                .map(assembleia -> modelMapper.map(assembleia, AssembleiaDto.class))
                .toList();
        log.info("<--- Response GET /v1/api/assembleias: {}", generateJson(assembleiasDto));
        return ResponseEntity.ok(assembleiasDto);
    }

    @PostMapping
    public ResponseEntity<AssembleiaDto> cadastrar(@Valid @RequestBody AssembleiaDto assembleiaDto) {
        log.info("---> Request POST /v1/api/assembleias: {}", generateJson(assembleiaDto));
        Assembleia assembleiaEntidade = modelMapper.map(assembleiaDto, Assembleia.class);
        Assembleia assembleiaCadastrada = assembleiaService.cadastrar(assembleiaEntidade);
        AssembleiaDto assembleiaCadastradaDto = modelMapper.map(assembleiaCadastrada, AssembleiaDto.class);
        log.info("<--- Response POST /v1/api/assembleias: {}", generateJson(assembleiaCadastradaDto));
        return ResponseEntity
                .status(CREATED)
                .body(assembleiaCadastradaDto);
    }
}