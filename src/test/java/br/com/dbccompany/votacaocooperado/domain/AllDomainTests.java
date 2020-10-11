package br.com.dbccompany.votacaocooperado.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PautaTest.class,
        VotoTest.class,
        SessaoVotacaoTest.class
})
public class AllDomainTests {
}
