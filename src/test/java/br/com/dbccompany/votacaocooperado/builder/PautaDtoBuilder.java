package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;

public final class PautaDtoBuilder {

    private final PautaDto pautaDto = new PautaDto();

    public static PautaDtoBuilder umaPauta() {
        return new PautaDtoBuilder();
    }

    public static PautaDtoBuilder umaPautaQualquer() {
        return umaPauta().comId(1L).comDecricao("Emissão de novas cotas do fundo Musical");
    }

    public PautaDtoBuilder comId(Long id) {
        pautaDto.setId(id);
        return this;
    }

    public PautaDtoBuilder comDecricao(String descricao) {
        pautaDto.setDescricao(descricao);
        return this;
    }

    public PautaDto build() {
        return pautaDto;
    }
}