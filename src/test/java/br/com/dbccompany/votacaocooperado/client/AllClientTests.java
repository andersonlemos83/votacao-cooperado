package br.com.dbccompany.votacaocooperado.client;

import br.com.dbccompany.votacaocooperado.client.impl.UsuarioClientImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        UsuarioClientImplTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Client package")
public class AllClientTests {

}