package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.service.impl.AssembleiaServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.AssociadoServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.PautaServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.VotoServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorAssembleiaImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorAssociadoImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorVotoImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssociadoServiceImplTest.class,
        PautaServiceImplTest.class,
        AssembleiaServiceImplTest.class,
        VotoServiceImplTest.class,
        ValidadorAssembleiaImplTest.class,
        ValidadorVotoImplTest.class,
        ValidadorAssociadoImplTest.class
})
public class AllServiceTests {
}
