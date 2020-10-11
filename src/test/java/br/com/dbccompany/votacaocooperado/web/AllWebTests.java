package br.com.dbccompany.votacaocooperado.web;

import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorAssembleiaImplTest;
import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorAssociadoImplTest;
import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorPautaImplTest;
import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorVotoImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConversorPautaImplTest.class,
        ConversorAssociadoImplTest.class,
        ConversorVotoImplTest.class,
        ConversorAssembleiaImplTest.class
})
public class AllWebTests {
}
