package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listatodososvotos;

import br.com.dbccompany.votacaocooperado.domain.TipoVoto;

public class VotoDataTable {

    private Long id;
    private TipoVoto tipoVoto;
    private String nomeAssociado;
    private String descricaoPauta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVoto getTipoVoto() {
        return tipoVoto;
    }

    public void setTipoVoto(TipoVoto tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

    public String getNomeAssociado() {
        return nomeAssociado;
    }

    public void setNomeAssociado(String nomeAssociado) {
        this.nomeAssociado = nomeAssociado;
    }

    public String getDescricaoPauta() {
        return descricaoPauta;
    }

    public void setDescricaoPauta(String descricaoPauta) {
        this.descricaoPauta = descricaoPauta;
    }
}