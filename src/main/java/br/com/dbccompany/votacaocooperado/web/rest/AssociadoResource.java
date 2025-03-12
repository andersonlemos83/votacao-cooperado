package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.service.AssociadoService;
import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;
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
@RequestMapping("/v1/api/associados")
public class AssociadoResource {

    private final AssociadoService associadoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AssociadoDto>> listar() {
        log.info("---> Request GET /v1/api/associados");
        List<Associado> associados = associadoService.listarTodos();
        List<AssociadoDto> associadosDto = associados.stream()
                .map(associado -> modelMapper.map(associado, AssociadoDto.class))
                .toList();
        log.info("<--- Response GET /v1/api/associados: {}", generateJson(associadosDto));
        return ResponseEntity.ok(associadosDto);
    }

    @PostMapping
    public ResponseEntity<AssociadoDto> cadastrar(@Valid @RequestBody AssociadoDto associadoDto) {
        log.info("---> Request POST /v1/api/associados: {}", generateJson(associadoDto));
        Associado associadoEntidade = modelMapper.map(associadoDto, Associado.class);
        Associado associadoCadastrado = associadoService.cadastrar(associadoEntidade);
        AssociadoDto associadoCadastradoDto = modelMapper.map(associadoCadastrado, AssociadoDto.class);
        log.info("<--- Response POST /v1/api/associados: {}", generateJson(associadoCadastradoDto));
        return ResponseEntity
                .status(CREATED)
                .body(associadoCadastradoDto);
    }
}