package br.com.dbccompany.votacaocooperado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"br.com.dbccompany.votacaocooperado.*"})
@SpringBootApplication(scanBasePackages = {"br.com.dbccompany.votacaocooperado"})
public class VotacaoCooperadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotacaoCooperadoApplication.class, args);
    }

}
