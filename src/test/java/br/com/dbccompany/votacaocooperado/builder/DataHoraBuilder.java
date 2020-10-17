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
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.set(Calendar.DAY_OF_MONTH, diaDoMes);
            }
        });
    }

    public DataHoraBuilder nDiasAtras(final int numeroDeDias) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.add(Calendar.DAY_OF_MONTH, -numeroDeDias);
            }
        });
    }

    public DataHoraBuilder comMes(final int mes) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.set(Calendar.MONTH, mes - 1);
            }
        });
    }

    public DataHoraBuilder comAno(final int ano) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.set(Calendar.YEAR, ano);
            }
        });
    }

    public DataHoraBuilder comHora(final int hora) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.set(Calendar.HOUR_OF_DAY, hora);
            }
        });
    }

    public DataHoraBuilder comMinuto(final int minuto) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.set(Calendar.MINUTE, minuto);
            }
        });
    }

    public DataHoraBuilder nMinutosAtras(final int numeroDeMinutos) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.add(Calendar.MINUTE, -numeroDeMinutos);
            }
        });
    }

    public DataHoraBuilder comSegundo(final int segundo) {
        return gerarNovaInstanciaDeDataHoraBuilder(new Command() {
            @Override
            public void execute(DataHoraBuilder DataHoraBuilder) {
                DataHoraBuilder.calendar.set(Calendar.SECOND, segundo);
            }
        });
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