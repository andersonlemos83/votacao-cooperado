package br.com.dbccompany.votacaocooperado.cucumber.stepdefs;

import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssembleiaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.PautaRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.VotoRepositoryTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;

import static java.text.MessageFormat.format;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inicializarContexto() {
        votoRepositoryTestHelper.deleteAll();
        assembleiaRepositoryTestHelper.deleteAll();
        associadoRepositoryTestHelper.deleteAll();
        pautaRepositoryTestHelper.deleteAll();
        resetarSequeces();
    }

    private void resetarSequeces() {
        List<Map<String, Object>> sequences = jdbcTemplate.queryForList("SELECT * FROM INFORMATION_SCHEMA.SEQUENCES");
        sequences.forEach(sequence -> {
            String sequence_name = (String) sequence.get("SEQUENCE_NAME");
            jdbcTemplate.execute(format("ALTER SEQUENCE {0} RESTART WITH 1", sequence_name));
        });
    }
}