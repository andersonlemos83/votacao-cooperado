package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.dbccompany.votacaocooperado.shared.util.ObjectMapperUtil.generateJson;
import static org.springframework.http.HttpStatus.CREATED;

@Log4j2
@RestController
@RequestMapping("/v1/api/votos")
@CrossOrigin(origins = "*")
public class VotoResource {

    private final VotoService votoService;
    private final ModelMapper modelMapper;

    public VotoResource(VotoService votoService,
                        ModelMapper modelMapper) {
        this.votoService = votoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<VotoDto>> listar() {
        log.info("---> Request GET /v1/api/votos");
        List<Voto> votos = votoService.listarTodos();
        List<VotoDto> votosDto = votos.stream()
                .map(voto -> modelMapper.map(voto, VotoDto.class))
                .toList();
        log.info("<--- Response GET /v1/api/votos: {}", generateJson(votosDto));
        return ResponseEntity.ok(votosDto);
    }

    @PostMapping
    public ResponseEntity<VotoDto> cadastrar(@Valid @RequestBody VotoDto votoDto) {
        log.info("---> Request POST /v1/api/votos: {}", generateJson(votoDto));
        Voto votoEntidade = modelMapper.map(votoDto, Voto.class);
        Voto votoCadastrado = votoService.cadastrar(votoEntidade);
        VotoDto votoCadastradoDto = modelMapper.map(votoCadastrado, VotoDto.class);
        log.info("<--- Response POST /v1/api/votos: {}", generateJson(votoCadastradoDto));
        return ResponseEntity
                .status(CREATED)
                .body(votoCadastradoDto);
    }
}