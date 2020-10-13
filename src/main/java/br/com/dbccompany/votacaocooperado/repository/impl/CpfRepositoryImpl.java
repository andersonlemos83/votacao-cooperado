package br.com.dbccompany.votacaocooperado.repository.impl;

import br.com.dbccompany.votacaocooperado.repository.CpfRepository;
import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.text.MessageFormat.format;

@Repository
public class CpfRepositoryImpl implements CpfRepository {

    // ATENÇÃO - Em um ambiente real, essa informação
    // seria recuperada de um servidor de configuração.
    private static final String URL_API_VALIDACAO_CPF = "https://user-info.herokuapp.com/users";

    private final RestTemplate restTemplate;

    public CpfRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean verificarSeEstaValido(String cpf) {
        try {
            restTemplate.getForObject(gerarUrl(cpf), Map.class);
            return true;
        } catch (HttpStatusCodeException excecao) {
            return false;
        } catch (Exception excecao) {
            throw new NegocioException("O serviço de validação do CPF está offline");
        }
    }

    private String gerarUrl(String cpf) {
        return format("{0}/{1}", URL_API_VALIDACAO_CPF, cpf);
    }
}