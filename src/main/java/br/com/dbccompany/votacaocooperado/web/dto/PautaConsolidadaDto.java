package br.com.dbccompany.votacaocooperado.web.dto;

import java.util.Date;

public class PautaConsolidadaDto {

    private String descricao;
    private Date dataCriacao;
    private int quantidadeVotosSim;
    private int quantidadeVotosNao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getQuantidadeVotosSim() {
        return quantidadeVotosSim;
    }

    public void setQuantidadeVotosSim(int quantidadeVotosSim) {
        this.quantidadeVotosSim = quantidadeVotosSim;
    }

    public int getQuantidadeVotosNao() {
        return quantidadeVotosNao;
    }

    public void setQuantidadeVotosNao(int quantidadeVotosNao) {
        this.quantidadeVotosNao = quantidadeVotosNao;
    }

    @Override
    public String toString() {
        return "ConsolidadoPautaDto{" +
                "descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", quantidadevotosSim=" + quantidadeVotosSim +
                ", quantidadevotosNao=" + quantidadeVotosNao +
                '}';
    }
}