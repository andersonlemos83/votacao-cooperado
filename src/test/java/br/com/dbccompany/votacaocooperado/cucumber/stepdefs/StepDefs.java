package br.com.dbccompany.votacaocooperado.cucumber.stepdefs;

import br.com.dbccompany.votacaocooperado.cucumber.configurador.ConfiguradorAmbiente;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.suporte.TransicaoDataTable;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefs {

    protected static TransicaoDataTable transicaoDataTable = TransicaoDataTable.builder().build();

    @Autowired
    private ConfiguradorAmbiente configuradorAmbiente;

    public void inicializarContexto() {
        configuradorAmbiente.configurarAmbiente();
        transicaoDataTable = TransicaoDataTable.builder().build();
    }
}