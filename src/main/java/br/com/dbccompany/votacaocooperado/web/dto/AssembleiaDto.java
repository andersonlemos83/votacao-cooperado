package br.com.dbccompany.votacaocooperado.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AssembleiaDto implements Serializable {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss.SSSZ", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataCriacao;
    private int tempoDuracao;
    @NotNull(message = "O id da pauta é obrigatório")
    private Long idPauta;
    private List<VotoDto> votos;

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

    public List<VotoDto> getVotos() {
        return votos;
    }

    public void setVotos(List<VotoDto> votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "AssembleiaDto{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", tempoDuracao=" + tempoDuracao +
                ", idPauta=" + idPauta +
                '}';
    }
}