package br.com.dbccompany.votacaocooperado.domain;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        PautaTest.class,
        VotoTest.class,
        AssembleiaTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Domain package")
public class AllDomainTests {

}