package br.com.dbccompany.votacaocooperado.web;

import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorAssembleiaImplTest;
import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorAssociadoImplTest;
import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorPautaImplTest;
import br.com.dbccompany.votacaocooperado.web.conversor.impl.ConversorVotoImplTest;
import br.com.dbccompany.votacaocooperado.web.rest.AssembleiaResourceTest;
import br.com.dbccompany.votacaocooperado.web.rest.AssociadoResourceTest;
import br.com.dbccompany.votacaocooperado.web.rest.PautaResourceTest;
import br.com.dbccompany.votacaocooperado.web.rest.VotoResourceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConversorPautaImplTest.class,
        ConversorAssociadoImplTest.class,
        ConversorVotoImplTest.class,
        ConversorAssembleiaImplTest.class,
        PautaResourceTest.class,
        AssociadoResourceTest.class,
        AssembleiaResourceTest.class,
        VotoResourceTest.class
})
public class AllWebTests {
}
