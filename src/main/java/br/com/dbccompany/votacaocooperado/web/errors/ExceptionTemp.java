package br.com.dbccompany.votacaocooperado.web.errors;

import java.time.OffsetDateTime;

public class ExceptionTemp {

    private String titulo;
    private OffsetDateTime dataHora;
    private Integer status;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
