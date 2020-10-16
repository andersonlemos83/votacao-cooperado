package br.com.dbccompany.votacaocooperado.web.rest;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;
import br.com.dbccompany.votacaocooperado.service.PautaService;
import br.com.dbccompany.votacaocooperado.web.conversor.ConversorPauta;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;
import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pauta")
@CrossOrigin(origins = "*")
public class PautaResource {

    private static final Logger log = LoggerFactory.getLogger(PautaResource.class);

    private final PautaService pautaService;
    private final ConversorPauta conversorPauta;
    private final ModelMapper modelMapper;

    public PautaResource(PautaService pautaService,
                         ConversorPauta conversorPauta,
                         ModelMapper modelMapper) {
        this.pautaService = pautaService;
        this.conversorPauta = conversorPauta;
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
//
//        final TypeMap<Pauta, PautaConsolidadaDto> typeMap = modelMapper.createTypeMap(Pauta.class, PautaConsolidadaDto.class);
//
//        final PautaConsolidadaDto map = typeMap.addMappings(mapper -> {
//            mapper.map(src -> src.obterUltimaAssembleia2().obterQuantidadeVotosSim(), PautaConsolidadaDto::setQuantidadeVotosSim);
////            mapper.map(origem -> origem.getDescricao(), PautaConsolidadaDto::setDescricao);
////            mapper.map(src -> src.obterUltimaAssembleia().get().getDataCriacao(), PautaConsolidadaDto::setDataCriacao);
////            mapper.map(src -> src.obterUltimaAssembleia().get().getDataCriacao(), PautaConsolidadaDto::setDataCriacao);
////            mapper.map(src -> src.obterUltimaAssembleia().get().obterStatusAssembleia(), PautaConsolidadaDto::setStatusAssembleia);
////            mapper.map(src -> src.obterUltimaAssembleia().get().obterQuantidadeVotosSim(), PautaConsolidadaDto::setQuantidadeVotosSim);
////            mapper.map(src -> src.obterUltimaAssembleia().get().obterQuantidadeVotosNao(), PautaConsolidadaDto::setQuantidadeVotosNao);
//        }).map(pautaRetornada);

        PautaConsolidadaDto consolidadoPautaDto = conversorPauta.converterParaConsolidada(pautaRetornada);
        return ResponseEntity.ok().body(consolidadoPautaDto);
    }

    private <S, D> Converter<S, D> converterWithDestinationSupplier(Supplier<? extends D> supplier ) {
        return ctx -> ctx.getMappingEngine().map(ctx.create(ctx.getSource(), supplier.get()));
    }
}