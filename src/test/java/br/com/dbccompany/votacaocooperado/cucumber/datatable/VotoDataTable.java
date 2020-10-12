package br.com.dbccompany.votacaocooperado.cucumber.datatable;

import br.com.dbccompany.votacaocooperado.domain.TipoVoto;

public class VotoDataTable {

    private Long id;
    private TipoVoto tipoVoto;
    private String nomeAssociado;
    private Long idAssociado;
    private String descricaoPauta;
    private Long idAssembleia;

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

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    public String getDescricaoPauta() {
        return descricaoPauta;
    }

    public void setDescricaoPauta(String descricaoPauta) {
        this.descricaoPauta = descricaoPauta;
    }

    public Long getIdAssembleia() {
        return idAssembleia;
    }

    public void setIdAssembleia(Long idAssembleia) {
        this.idAssembleia = idAssembleia;
    }
}