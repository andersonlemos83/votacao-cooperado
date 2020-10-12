package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.repositorytesthelper.AssociadoRepositoryTestHelper;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class AssociadoVerificador {

    @Autowired
    private AssociadoRepositoryTestHelper associadoRepositoryTestHelper;

    public void verificar(List<AssociadoDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].nome", i)).value(esperados.get(i).getNome()))
                    .andExpect(jsonPath(format("$.[{0}].cpf", i)).value(esperados.get(i).getCpf()));
        }
    }

    public void verificar(AssociadoDataTable associadoDataTable, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Optional<Associado> associadoOptional = Optional.ofNullable(associadoRepositoryTestHelper.findByCpf(associadoDataTable.getCpf()));
        Associado associado = associadoOptional.orElse(new Associado());
        assertEquals(associadoDataTable.getNome(), associado.getNome());
        assertEquals(associadoDataTable.getCpf(), associado.getCpf());
    }
}