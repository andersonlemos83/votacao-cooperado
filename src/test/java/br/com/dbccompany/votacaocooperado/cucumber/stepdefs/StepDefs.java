package br.com.dbccompany.votacaocooperado.cucumber.stepdefs;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.VotoRepositoryTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VotacaoCooperadoApplication.class)
public class StepDefs {

    protected static ResultActions retorno;

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
        votoRepositoryTestHelper.flush();
        assembleiaRepositoryTestHelper.deleteAll();
        assembleiaRepositoryTestHelper.flush();
        associadoRepositoryTestHelper.deleteAll();
        associadoRepositoryTestHelper.findAll();
        pautaRepositoryTestHelper.deleteAll();
        pautaRepositoryTestHelper.flush();
    }
}