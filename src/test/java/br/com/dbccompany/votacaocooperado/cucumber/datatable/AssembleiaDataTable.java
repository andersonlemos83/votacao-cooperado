package br.com.dbccompany.votacaocooperado.cucumber.datatable;

import br.com.dbccompany.votacaocooperado.builder.DataHoraBuilder;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

import static java.text.MessageFormat.format;
import static lombok.AccessLevel.NONE;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssembleiaDataTable implements Serializable {

    private Long id;
    @Getter(NONE)
    private String dataCriacao;
    private int tempoDuracao;
    private String descricaoPauta;
    private Long idPauta;

    public String getDataCriacao() {
        if ("DATA_ATUAL".equalsIgnoreCase(dataCriacao)) {
            return format("{0,date,dd/MM/yyyy HH:mm}", new Date());
        }
        return dataCriacao;
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