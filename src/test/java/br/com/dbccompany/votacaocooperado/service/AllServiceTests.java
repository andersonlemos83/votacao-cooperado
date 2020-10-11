package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.service.impl.AssociadoServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.PautaServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.SessaoVotacaoServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.VotoServiceImplTest;
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
