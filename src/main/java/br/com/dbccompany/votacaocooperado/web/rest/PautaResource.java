package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
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
@RequestMapping("/v1/api/pautas")
@CrossOrigin(origins = "*")
public class PautaResource {

    private static final Logger log = LoggerFactory.getLogger(PautaResource.class);

    private final PautaService pautaService;
    private final ModelMapper modelMapper;

    public PautaResource(PautaService pautaService,
                         ModelMapper modelMapper) {
        this.pautaService = pautaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PautaDto>> listarTodos() {
        log.info("Requisição Rest para listar todas as pautas");
        List<Pauta> pautas = pautaService.listarTodos();
        List<PautaDto> pautasDto = pautas.stream()
                .map(pauta -> modelMapper.map(pauta, PautaDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pautasDto);
    }

    @PostMapping
    public ResponseEntity<PautaDto> cadastrar(@Valid @RequestBody PautaDto pautaDto) {
        log.info("Requisição Rest para cadastrar pauta: {}", pautaDto);
        Pauta pautaEntidade = modelMapper.map(pautaDto, Pauta.class);
        Pauta pautaCadastrada = pautaService.cadastrar(pautaEntidade);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(pautaCadastrada, PautaDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaConsolidadaDto> buscarPorId(@PathVariable Long id) {
        log.info("Requisição Rest para buscar pauta por id: {}", id);
        Pauta pautaRetornada = pautaService.buscarPorId(id);
        PautaConsolidadaDto consolidadoPautaDto = modelMapper.map(pautaRetornada, PautaConsolidadaDto.class);
        return ResponseEntity.ok().body(consolidadoPautaDto);
    }
}