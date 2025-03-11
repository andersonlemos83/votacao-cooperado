package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Voto;
import br.com.dbccompany.votacaocooperado.service.VotoService;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;
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
@RequestMapping("/v1/api/votos")
@CrossOrigin(origins = "*")
public class VotoResource {

    private static final Logger log = LoggerFactory.getLogger(VotoResource.class);

    private final VotoService votoService;
    private final ModelMapper modelMapper;

    public VotoResource(VotoService votoService,
                        ModelMapper modelMapper) {
        this.votoService = votoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<VotoDto>> listar() {
        log.info("Requisição Rest para listar todos os votos");
        List<Voto> votos = votoService.listarTodos();
        List<VotoDto> votosDto = votos.stream()
                .map(voto -> modelMapper.map(voto, VotoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(votosDto);
    }

    @PostMapping
    public ResponseEntity<VotoDto> cadastrar(@Valid @RequestBody VotoDto votoDto) {
        log.info("Requisição Rest para cadastrar voto: {}", votoDto);
        Voto votoEntidade = modelMapper.map(votoDto, Voto.class);
        Voto votoCadastrado = votoService.cadastrar(votoEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(votoCadastrado, VotoDto.class));
    }
}