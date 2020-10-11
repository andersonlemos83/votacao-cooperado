package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorSessaoVotacaoImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorVotoImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssociadoServiceImplTest.class,
        PautaServiceImplTest.class,
        SessaoVotacaoServiceImplTest.class,
        VotoServiceImplTest.class,
        ValidadorSessaoVotacaoImplTest.class,
        ValidadorVotoImplTest.class
})
public class AllServiceTests {
}
