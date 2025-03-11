package br.com.dbccompany.votacaocooperado.service;

import br.com.dbccompany.votacaocooperado.service.impl.AssembleiaServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.AssociadoServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.PautaServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.impl.VotoServiceImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorAssembleiaImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorAssociadoImplTest;
import br.com.dbccompany.votacaocooperado.service.validador.impl.ValidadorVotoImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        AssociadoServiceImplTest.class,
        PautaServiceImplTest.class,
        AssembleiaServiceImplTest.class,
        VotoServiceImplTest.class,
        ValidadorAssembleiaImplTest.class,
        ValidadorVotoImplTest.class,
        ValidadorAssociadoImplTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Service package")
public class AllServiceTests {

}