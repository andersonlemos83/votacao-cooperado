package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
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
@RequestMapping("/v1/api/pautas")
@CrossOrigin(origins = "*")
public class PautaResource {

    private final PautaService pautaService;
    private final ModelMapper modelMapper;

    public PautaResource(PautaService pautaService,
                         ModelMapper modelMapper) {
        this.pautaService = pautaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PautaDto>> listarTodos() {
        log.info("---> Request GET /v1/api/pautas");
        List<Pauta> pautas = pautaService.listarTodos();
        List<PautaDto> pautasDto = pautas.stream()
                .map(pauta -> modelMapper.map(pauta, PautaDto.class))
                .toList();
        log.info("<--- Response GET /v1/api/pautas: {}", generateJson(pautasDto));
        return ResponseEntity.ok(pautasDto);
    }

    @PostMapping
    public ResponseEntity<PautaDto> cadastrar(@Valid @RequestBody PautaDto pautaDto) {
        log.info("---> Request POST /v1/api/pautas: {}", generateJson(pautaDto));
        Pauta pautaEntidade = modelMapper.map(pautaDto, Pauta.class);
        Pauta pautaCadastrada = pautaService.cadastrar(pautaEntidade);
        PautaDto pautaCadastradaDto = modelMapper.map(pautaCadastrada, PautaDto.class);
        log.info("<--- Response POST /v1/api/pautas: {}", generateJson(pautaCadastradaDto));
        return ResponseEntity
                .status(CREATED)
                .body(pautaCadastradaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaConsolidadaDto> buscarPorId(@PathVariable Long id) {
        log.info("---> Request GET /v1/api/pautas/{}", generateJson(id));
        Pauta pautaRetornada = pautaService.buscarPorId(id);
        PautaConsolidadaDto consolidadoPautaDto = modelMapper.map(pautaRetornada, PautaConsolidadaDto.class);
        log.info("<--- Response GET /v1/api/pautas/{}: {}", generateJson(id), generateJson(consolidadoPautaDto));
        return ResponseEntity.ok().body(consolidadoPautaDto);
    }
}