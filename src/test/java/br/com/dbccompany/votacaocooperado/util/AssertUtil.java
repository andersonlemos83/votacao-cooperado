package br.com.dbccompany.votacaocooperado.util;

import java.util.Date;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;

public final class AssertUtil {

    private static final String FORMATO_DD_MM_YYYY_HH_MM = "{0,date,dd/MM/yyyy HH:mm}";

    private AssertUtil() {
    }

    public static void assertData(String dataEsperada, Date dataRetornada) {
        String dataRetornadaFormatada = format(FORMATO_DD_MM_YYYY_HH_MM, dataRetornada);
        assertEquals(dataEsperada, dataRetornadaFormatada);
    }

    public static void assertData(Date dataEsperada, Date dataRetornada) {
        String dataEsperadaFormatada = format(FORMATO_DD_MM_YYYY_HH_MM, dataEsperada);
        String dataRetornadaFormatada = format(FORMATO_DD_MM_YYYY_HH_MM, dataRetornada);
        assertEquals(dataEsperadaFormatada, dataRetornadaFormatada);
    }
}