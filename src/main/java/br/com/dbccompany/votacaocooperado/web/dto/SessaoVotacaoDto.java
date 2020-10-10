package br.com.dbccompany.votacaocooperado.web.dto;

import java.io.Serializable;
import java.util.Date;

public class SessaoVotacaoDto implements Serializable {

    private Long id;
    private Date dataCriacao;
    private int tempoDuracao;
    private Long idPauta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    @Override
    public String toString() {
        return "SessaoVotacaoDto{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", tempoDuracao=" + tempoDuracao +
                ", idPauta=" + idPauta +
                '}';
    }
}