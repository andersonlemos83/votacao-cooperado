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

    @NotNull(message = "O id da sessão de votação é obrigatório")
    private Long idSessaoVotacao;

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

    public Long getIdSessaoVotacao() {
        return idSessaoVotacao;
    }

    public void setIdSessaoVotacao(Long idSessaoVotacao) {
        this.idSessaoVotacao = idSessaoVotacao;
    }

    @Override
    public String toString() {
        return "VotoDto{" +
                "id=" + id +
                ", tipoVoto=" + tipoVoto +
                ", idAssociado=" + idAssociado +
                ", idSessaoVotacao=" + idSessaoVotacao +
                '}';
    }
}