package br.com.dbccompany.votacaocooperado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VotacaoCooperadoConfigTest {

    @Bean
    @Primary
    public RestTemplateTest restTemplate() {
        return new RestTemplateTest();
    }

    private class RestTemplateTest extends RestTemplate {

        @Nullable
        public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
            // Contexto do cenário: 06 - Cadastrar Associado Com Servico de Validacao de CPF Offline
            if (verificaSeUrlContemCpfDeRussellHitchcock(url)) {
                throw new RuntimeException("Erro de timeout!");
            }
            return super.getForObject(url, responseType, uriVariables);
        }

        private boolean verificaSeUrlContemCpfDeRussellHitchcock(String url) {
            return url.contains("61022326074");
        }
    }
}