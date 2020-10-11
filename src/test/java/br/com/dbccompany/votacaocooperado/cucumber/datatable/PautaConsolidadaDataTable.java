package br.com.dbccompany.votacaocooperado.cucumber.datatable;

import br.com.dbccompany.votacaocooperado.domain.StatusAssembleia;

public class PautaConsolidadaDataTable {

    private String descricaoPauta;
    private StatusAssembleia statusAssembleia;
    private int quantidadeVotosSim;
    private int quantidadeVotosNao;

    public String getDescricaoPauta() {
        return descricaoPauta;
    }

    public void setDescricaoPauta(String descricaoPauta) {
        this.descricaoPauta = descricaoPauta;
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
}