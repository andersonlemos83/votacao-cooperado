package br.com.dbccompany.votacaocooperado.config;

import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

@Configuration
public class VotacaoCooperadoConfig {

    @Primary
    @Bean("gitProperties")
    public GitProperties gitProperties() {
        Properties properties = new Properties();
        properties.put("branch", "test");
        properties.put("commit.id", "c4f908dbfef2699ef5d024301d3eb579b12198ea");
        properties.put("commit.id.abbrev", "c4f908d");
        properties.put("commit.time", "2025-03-03T19\\:18\\:33-0300");
        properties.put("build.time", "2025-03-03T19\\:18\\:33-0300");
        return new GitProperties(properties);
    }

    @Primary
    @Bean("buildProperties")
    public BuildProperties buildProperties() {
        Properties properties = new Properties();
        properties.put("group", "br.com.dbccompany");
        properties.put("artifact", "votacao-cooperado");
        properties.put("name", "votacao-cooperado");
        properties.put("version", "1.0.0-TEST");
        properties.put("time", "2025-03-03T19\\:18\\:33-0300");
        return new BuildProperties(properties);
    }
}