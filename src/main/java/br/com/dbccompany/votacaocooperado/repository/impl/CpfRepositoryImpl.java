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
            throw new NegocioException("O serviço de validação do CPF está offiline");
        }
    }

    private String gerarUrl(String cpf) {
        String urlBase = "https://user-info.herokuapp.com/users";
        return format("{0}/{1}", urlBase, cpf);
    }
}