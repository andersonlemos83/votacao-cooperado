package br.com.dbccompany.votacaocooperado.helper.gerenciador.impl;

import br.com.dbccompany.votacaocooperado.helper.gerenciador.GerenciadorH2;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.text.MessageFormat.format;

@Log4j2
@Component
@AllArgsConstructor
public class GerenciadorH2Impl implements GerenciadorH2 {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void removerChavesEstrangeiras() {
        List<Map<String, Object>> constraints = jdbcTemplate.queryForList("SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS");
        constraints.stream()
                .parallel()
                .filter(constraint -> "COOP_OWNER".equals(constraint.get("TABLE_SCHEMA")) && "FOREIGN KEY".equals(constraint.get("CONSTRAINT_TYPE")))
                .forEach(constraint -> {
                    String table_schema = (String) constraint.get("TABLE_SCHEMA");
                    String table_name = (String) constraint.get("TABLE_NAME");
                    String constraint_name = (String) constraint.get("CONSTRAINT_NAME");
                    String truncateSql = format("ALTER TABLE {0}.{1} DROP CONSTRAINT {2} RESTRICT", table_schema, table_name, constraint_name);
                    log.info("--> {}", truncateSql);
                    jdbcTemplate.execute(truncateSql);
                });
    }

    @Override
    public void limparBanco() {
        List<Map<String, Object>> tables = jdbcTemplate.queryForList("SELECT * FROM INFORMATION_SCHEMA.TABLES");
        tables.stream()
                .parallel()
                .filter(table -> "COOP_OWNER".equals(table.get("TABLE_SCHEMA")))
                .forEach(table -> {
                    String table_schema = (String) table.get("TABLE_SCHEMA");
                    String table_name = (String) table.get("TABLE_NAME");
                    String truncateSql = format("TRUNCATE TABLE {0}.{1}", table_schema, table_name);
                    log.info("--> {}", truncateSql);
                    jdbcTemplate.execute(truncateSql);
                });
    }

    @Override
    public void resetarSequences() {
        List<Map<String, Object>> sequences = jdbcTemplate.queryForList("SELECT * FROM INFORMATION_SCHEMA.SEQUENCES");
        sequences.forEach(sequence -> {
            String sequence_schema = (String) sequence.get("SEQUENCE_SCHEMA");
            String sequence_name = (String) sequence.get("SEQUENCE_NAME");
            jdbcTemplate.execute(format("ALTER SEQUENCE {0}.{1} RESTART WITH 1", sequence_schema, sequence_name));
        });
    }
}