package br.com.dbccompany.votacaocooperado.cucumber.datatable;

import br.com.dbccompany.votacaocooperado.builder.DataHoraBuilder;

import java.util.Date;

import static java.text.MessageFormat.format;

public class AssembleiaDataTable {

    private Long id;
    private String dataCriacao;
    private int tempoDuracao;
    private String descricaoPauta;
    private Long idPauta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCriacao() {
        if ("DATA_ATUAL".equalsIgnoreCase(dataCriacao)) {
            return format("{0,date,dd/MM/yyyy HH:mm}", new Date());
        }
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public String getDescricaoPauta() {
        return descricaoPauta;
    }

    public void setDescricaoPauta(String descricaoPauta) {
        this.descricaoPauta = descricaoPauta;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public Date obterDataCriacao() {
        if ("DATA_ATUAL".equalsIgnoreCase(dataCriacao)) {
            return DataHoraBuilder.umaData().build();
        }

        if ("DATA_EXPIRADA_TRES_MINUTOS".equalsIgnoreCase(dataCriacao)) {
            return DataHoraBuilder.umaData().nMinutosAtras(3).build();
        }
        return null;
    }
}