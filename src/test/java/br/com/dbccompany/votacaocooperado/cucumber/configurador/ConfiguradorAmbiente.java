package br.com.dbccompany.votacaocooperado.cucumber.configurador;

import br.com.dbccompany.votacaocooperado.helper.gerenciador.GerenciadorH2;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class ConfiguradorAmbiente {

    private final GerenciadorH2 gerenciadorH2;
    private final WireMockServer wireMockServer;

    public void configurarAmbiente() {
        log.info("INICIO - Inicializando Contexto");
        gerenciadorH2.removerChavesEstrangeiras();
        gerenciadorH2.limparBanco();
        gerenciadorH2.resetarSequences();
        wireMockServer.resetAll();
        log.info("FIM - Inicializando Contexto");
    }
}