package br.com.dbccompany.votacaocooperado.web;

import br.com.dbccompany.votacaocooperado.web.rest.AssembleiaResourceTest;
import br.com.dbccompany.votacaocooperado.web.rest.AssociadoResourceTest;
import br.com.dbccompany.votacaocooperado.web.rest.PautaResourceTest;
import br.com.dbccompany.votacaocooperado.web.rest.VotoResourceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        PautaResourceTest.class,
        AssociadoResourceTest.class,
        AssembleiaResourceTest.class,
        VotoResourceTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Web package")
public class AllWebTests {

}