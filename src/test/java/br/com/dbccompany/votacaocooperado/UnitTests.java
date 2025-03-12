package br.com.dbccompany.votacaocooperado;

import br.com.dbccompany.votacaocooperado.client.AllClientTests;
import br.com.dbccompany.votacaocooperado.domain.AllDomainTests;
import br.com.dbccompany.votacaocooperado.service.AllServiceTests;
import br.com.dbccompany.votacaocooperado.shared.AllSharedTests;
import br.com.dbccompany.votacaocooperado.web.AllWebTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        AllClientTests.class,
        AllDomainTests.class,
        AllServiceTests.class,
        AllSharedTests.class,
        AllWebTests.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all Unit tests")
public class UnitTests {

}