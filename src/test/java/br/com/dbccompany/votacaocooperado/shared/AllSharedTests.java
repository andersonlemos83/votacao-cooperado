package br.com.dbccompany.votacaocooperado.shared;

import br.com.dbccompany.votacaocooperado.shared.util.ObjectMapperUtilTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        ObjectMapperUtilTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Shared package")
public class AllSharedTests {

}