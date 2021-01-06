package br.com.dbccompany.votacaocooperado.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataHoraBuilder {

    private Calendar calendar = GregorianCalendar.getInstance();

    public static DataHoraBuilder umaData() {
        return new DataHoraBuilder();
    }

    private DataHoraBuilder() {

    }

    private DataHoraBuilder(DataHoraBuilder DataHoraBuilder) {
        this.calendar = DataHoraBuilder.calendar;
    }

    public DataHoraBuilder comDiaDoMes(final int diaDoMes) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.set(Calendar.DAY_OF_MONTH, diaDoMes);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder nDiasAtras(final int numeroDeDias) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.add(Calendar.DAY_OF_MONTH, -numeroDeDias);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder comMes(final int mes) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.set(Calendar.MONTH, mes - 1);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder comAno(final int ano) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.set(Calendar.YEAR, ano);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder comHora(final int hora) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.set(Calendar.HOUR_OF_DAY, hora);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder comMinuto(final int minuto) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.set(Calendar.MINUTE, minuto);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder nMinutosAtras(final int numeroDeMinutos) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.add(Calendar.MINUTE, -numeroDeMinutos);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public DataHoraBuilder comSegundo(final int segundo) {
        final Command command = DataHoraBuilder -> DataHoraBuilder.calendar.set(Calendar.SECOND, segundo);
        return gerarNovaInstanciaDeDataHoraBuilder(command);
    }

    public Date build() {
        return new Date(calendar.getTimeInMillis());
    }

    private DataHoraBuilder gerarNovaInstanciaDeDataHoraBuilder(Command command) {
        DataHoraBuilder DataHoraBuilder = new DataHoraBuilder(this);
        command.execute(DataHoraBuilder);
        return DataHoraBuilder;
    }

    private interface Command {
        void execute(DataHoraBuilder DataHoraBuilder);
    }
}