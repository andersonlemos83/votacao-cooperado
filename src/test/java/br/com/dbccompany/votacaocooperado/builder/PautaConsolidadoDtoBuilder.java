package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.domain.StatusAssembleia;
import br.com.dbccompany.votacaocooperado.web.dto.PautaConsolidadaDto;

import java.util.Date;

import static br.com.dbccompany.votacaocooperado.domain.StatusAssembleia.ABERTA;

public final class PautaConsolidadoDtoBuilder {

    private final PautaConsolidadaDto pautaConsolidadaDto = new PautaConsolidadaDto();

    public static PautaConsolidadoDtoBuilder umaPautaConsolidada() {
        return new PautaConsolidadoDtoBuilder();
    }

    public static PautaConsolidadoDtoBuilder umaPautaConsolidadaQualquer() {
        return umaPautaConsolidada()
                .comDecricao("Emissão de novas cotas do fundo Musical")
                .comDataCriacao(new Date())
                .comStatusAssembleia(ABERTA)
                .comQuantidadeVotosSim(0)
                .comQuantidadeVotosNao(0);
    }

    public PautaConsolidadoDtoBuilder comDecricao(String descricao) {
        pautaConsolidadaDto.setDescricao(descricao);
        return this;
    }

    public PautaConsolidadoDtoBuilder comDataCriacao(Date dataCriacao) {
        pautaConsolidadaDto.setDataCriacao(dataCriacao);
        return this;
    }

    public PautaConsolidadoDtoBuilder comStatusAssembleia(StatusAssembleia statusAssembleia) {
        pautaConsolidadaDto.setStatusAssembleia(statusAssembleia);
        return this;
    }

    public PautaConsolidadoDtoBuilder comQuantidadeVotosSim(int quantidadeVotosSim) {
        pautaConsolidadaDto.setQuantidadeVotosSim(quantidadeVotosSim);
        return this;
    }

    public PautaConsolidadoDtoBuilder comQuantidadeVotosNao(int quantidadeVotosNao) {
        pautaConsolidadaDto.setQuantidadeVotosNao(quantidadeVotosNao);
        return this;
    }

    public PautaConsolidadaDto build() {
        return pautaConsolidadaDto;
    }
}