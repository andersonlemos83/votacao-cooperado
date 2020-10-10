package br.com.dbccompany.votacaocooperado.web.dto;

import br.com.dbccompany.votacaocooperado.domain.TipoVoto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VotoDto implements Serializable {

    private Long id;

    @NotNull(message = "O tipo de voto é obrigatório")
    private TipoVoto tipoVoto;

    @NotNull(message = "O id do associado é obrigatório")
    private Long idAssociado;

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

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    @Override
    public String toString() {
        return "VotoDto{" +
                "id=" + id +
                ", tipoVoto=" + tipoVoto +
                ", idAssociado=" + idAssociado +
                '}';
    }
}