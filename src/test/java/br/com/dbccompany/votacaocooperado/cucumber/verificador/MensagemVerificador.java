package br.com.dbccompany.votacaocooperado.cucumber.verificador;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class MensagemVerificador {

    public void verificar(String mensagem, ResultActions retorno) throws Exception {
        retorno.andExpect(status().isBadRequest()).andExpect(jsonPath("$.mensagem").value(mensagem));
    }
}