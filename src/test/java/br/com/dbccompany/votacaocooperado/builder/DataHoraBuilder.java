package br.com.dbccompany.votacaocooperado.builder;

import java.time.LocalDateTime;
import java.util.Date;

import static java.time.ZoneId.systemDefault;

public class DataHoraBuilder {

    private LocalDateTime localDateTime = LocalDateTime.now();

    public static DataHoraBuilder umaData() {
        return new DataHoraBuilder();
    }

    private DataHoraBuilder() {

    }

    public DataHoraBuilder comDiaDoMes(final int diaDoMes) {
        this.localDateTime = this.localDateTime.withDayOfMonth(diaDoMes);
        return this;
    }

    public DataHoraBuilder nDiasAtras(final int numeroDeDias) {
        this.localDateTime = localDateTime.minusDays(numeroDeDias);
        return this;
    }

    public DataHoraBuilder comMes(final int mes) {
        this.localDateTime = localDateTime.withMonth(mes);
        return this;
    }

    public DataHoraBuilder comAno(final int ano) {
        this.localDateTime = localDateTime.withYear(ano);
        return this;
    }

    public DataHoraBuilder comHora(final int hora) {
        this.localDateTime = localDateTime.withHour(hora);
        return this;
    }

    public DataHoraBuilder comMinuto(final int minuto) {
        this.localDateTime = localDateTime.withMinute(minuto);
        return this;
    }

    public DataHoraBuilder nMinutosAtras(final int numeroDeMinutos) {
        this.localDateTime = localDateTime.minusMinutes(numeroDeMinutos);
        return this;
    }

    public DataHoraBuilder comSegundo(final int segundo) {
        this.localDateTime = localDateTime.withSecond(segundo);
        return this;
    }

    public Date build() {
        return Date.from(localDateTime.atZone(systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        final DataHoraBuilder dataHoraBuilder = DataHoraBuilder.umaData().nMinutosAtras(3);
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.withDayOfMonth(1);
        System.out.println(dataHoraBuilder.build());
    }
}