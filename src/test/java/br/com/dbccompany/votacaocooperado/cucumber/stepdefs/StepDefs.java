package br.com.dbccompany.votacaocooperado.cucumber.stepdefs;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasasassembleias.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodasaspautas.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listartodososassociados.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.listatodososvotos.VotoRepositoryTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VotacaoCooperadoApplication.class)
public class StepDefs {

    @Autowired
    private VotoRepositoryTestHelper votoRepositoryTestHelper;

    @Autowired
    private AssembleiaRepositoryTestHelper assembleiaRepositoryTestHelper;

    @Autowired
    private AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    @Autowired
    private PautaRepositoryTestHelper pautaRepositoryTestHelper;

    public void inicializarContexto() {
        votoRepositoryTestHelper.deleteAll();
        assembleiaRepositoryTestHelper.deleteAll();
        associadoRepositoryTestHelper.deleteAll();
        pautaRepositoryTestHelper.deleteAll();
    }
}