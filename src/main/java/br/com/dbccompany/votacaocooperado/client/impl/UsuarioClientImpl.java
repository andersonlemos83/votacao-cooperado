package br.com.dbccompany.votacaocooperado.client.impl;

import br.com.dbccompany.votacaocooperado.client.UsuarioClient;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.text.MessageFormat.format;

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
            restTemplate.getForObject(gerarUrl(cpf), String.class);
            return true;
        } catch (HttpClientErrorException.BadRequest excecao) {
            return false;
        } catch (Exception excecao) {
            throw new NegocioException("O serviço de validação do CPF está offline");
        }
    }

    private String gerarUrl(String cpf) {
        return format("{0}/{1}", url, cpf);
    }
}