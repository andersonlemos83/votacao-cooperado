package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import br.com.dbccompany.votacaocooperado.web.dto.VotoDto;

import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.SIM;

public final class VotoDtoBuilder {

    private final VotoDto votoDto = new VotoDto();

    public static VotoDtoBuilder umVoto() {
        return new VotoDtoBuilder();
    }

    public static VotoDtoBuilder umVotoQualquer() {
        return umVoto()
                .comId(1L)
                .comTipoVoto(SIM)
                .comAssociado(1L)
                .comAssembleia(1L);
    }

    public VotoDtoBuilder comId(Long id) {
        votoDto.setId(id);
        return this;
    }

    public VotoDtoBuilder comTipoVoto(TipoVoto tipoVoto) {
        votoDto.setTipoVoto(tipoVoto);
        return this;
    }

    public VotoDtoBuilder comAssociado(Long idAssociado) {
        votoDto.setIdAssociado(idAssociado);
        return this;
    }

    public VotoDtoBuilder comAssembleia(Long idAssembleia) {
        votoDto.setIdAssembleia(idAssembleia);
        return this;
    }

    public VotoDto build() {
        return votoDto;
    }
}