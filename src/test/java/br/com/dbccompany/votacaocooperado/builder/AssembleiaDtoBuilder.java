package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.web.dto.AssembleiaDto;

import java.util.Date;

public final class AssembleiaDtoBuilder {

    private AssembleiaDto assembleiaDto = new AssembleiaDto();

    public static AssembleiaDtoBuilder umaAssembleia() {
        return new AssembleiaDtoBuilder();
    }

    public static AssembleiaDtoBuilder umaAssembleiaQualquer() {
        return umaAssembleia()
                .comId(1l)
                .comDataCriacao(new Date())
                .comTempoDuracao(1)
                .comPauta(1l);
    }

    public AssembleiaDtoBuilder comId(Long id) {
        assembleiaDto.setId(id);
        return this;
    }

    public AssembleiaDtoBuilder comDataCriacao(Date dataCriacao) {
        assembleiaDto.setDataCriacao(dataCriacao);
        return this;
    }

    public AssembleiaDtoBuilder comTempoDuracao(int tempoDuracao) {
        assembleiaDto.setTempoDuracao(tempoDuracao);
        return this;
    }

    public AssembleiaDtoBuilder comPauta(Long idPauta) {
        assembleiaDto.setIdPauta(idPauta);
        return this;
    }

    public AssembleiaDto build() {
        return assembleiaDto;
    }
}