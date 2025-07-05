package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import br.com.dbccompany.votacaocooperado.cucumber.datatable.domain.AssociadoDataTable;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.helper.repository.AssociadoRepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@AllArgsConstructor
public class AssociadoVerificador {

    private final AssociadoRepositoryHelper associadoRepositoryHelper;

    public void verificar(List<AssociadoDataTable> esperados, ResultActions retorno) throws Exception {
        for (int i = 0; i < esperados.size(); i++) {
            retorno.andExpect(status().isOk())
                    .andExpect(jsonPath(format("$.[{0}].nome", i)).value(esperados.get(i).getNome()))
                    .andExpect(jsonPath(format("$.[{0}].cpf", i)).value(esperados.get(i).getCpf()));
        }
    }

    public void verificar(AssociadoDataTable associadoDataTable, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isCreated());
        Associado associado = consultarAssociado(associadoDataTable);
        assertEquals(associadoDataTable.getNome(), associado.getNome());
        assertEquals(associadoDataTable.getCpf(), associado.getCpf());
    }

    private Associado consultarAssociado(AssociadoDataTable associadoDataTable) {
        Optional<Associado> associadoOptional = Optional.ofNullable(associadoRepositoryHelper.findByCpf(associadoDataTable.getCpf()));
        return associadoOptional.orElse(new Associado());
    }
}