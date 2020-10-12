package br.com.dbccompany.votacaocooperado;

import br.com.dbccompany.votacaocooperado.domain.AllDomainTests;
import br.com.dbccompany.votacaocooperado.repository.impl.AllRepositoryTests;
import br.com.dbccompany.votacaocooperado.service.AllServiceTests;
import br.com.dbccompany.votacaocooperado.web.AllWebTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllDomainTests.class,
        AllRepositoryTests.class,
        AllServiceTests.class,
        AllWebTests.class
})
public class UnitTests {
}
