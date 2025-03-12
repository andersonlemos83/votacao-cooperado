package br.com.dbccompany.votacaocooperado.client.impl;

import br.com.dbccompany.votacaocooperado.client.UsuarioClient;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static br.com.dbccompany.votacaocooperado.shared.util.ObjectMapperUtil.generateJson;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.exception.ExceptionUtils.getMessage;

@Log4j2
@Repository
public class UsuarioClientImpl implements UsuarioClient {

    private final RestTemplate restTemplate;
    private final String url;

    public UsuarioClientImpl(RestTemplate restTemplate,
                             @Value("${client.usuario.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public boolean verificarSeEstaValido(String cpf) {
        try {
            log.info("---> Request GET /users/{}", generateJson(cpf));
            String response = restTemplate.getForObject(gerarUrl(cpf), String.class);
            log.info("<--- Response GET /users/{}: {}", generateJson(cpf), generateJson(response));
            return true;
        } catch (HttpClientErrorException.BadRequest excecao) {
            log.error("Erro em UsuarioClientImpl: {}", getMessage(excecao), excecao);
            return false;
        } catch (Exception excecao) {
            log.error("Erro em UsuarioClientImpl: {}", getMessage(excecao), excecao);
            throw new NegocioException("O serviço de validação do CPF está offline");
        }
    }

    private String gerarUrl(String cpf) {
        return format("{0}/users/{1}", url, cpf);
    }
}