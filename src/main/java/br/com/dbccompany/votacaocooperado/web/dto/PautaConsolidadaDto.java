package br.com.dbccompany.votacaocooperado.web.dto;

import br.com.dbccompany.votacaocooperado.domain.StatusAssembleia;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PautaConsolidadaDto {

    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss.SSSZ", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataCriacao;
    private StatusAssembleia statusAssembleia;
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

    public StatusAssembleia getStatusAssembleia() {
        return statusAssembleia;
    }

    public void setStatusAssembleia(StatusAssembleia statusAssembleia) {
        this.statusAssembleia = statusAssembleia;
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
        return "PautaConsolidadaDto{" +
                "descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", statusAssembleia=" + statusAssembleia +
                ", quantidadeVotosSim=" + quantidadeVotosSim +
                ", quantidadeVotosNao=" + quantidadeVotosNao +
                '}';
    }
}