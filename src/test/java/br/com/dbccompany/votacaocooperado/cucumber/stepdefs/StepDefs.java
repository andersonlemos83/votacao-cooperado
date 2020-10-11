package br.com.dbccompany.votacaocooperado.cucumber.stepdefs;

import br.com.dbccompany.votacaocooperado.VotacaoCooperadoApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VotacaoCooperadoApplication.class)
public class StepDefs {

//    @Autowired
//    private WebServiceClientStub webServiceClientStub;

    public void inicializarContexto() {
//        webServiceClientStub.limpar();
    }
}