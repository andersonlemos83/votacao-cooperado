package br.com.dbccompany.votacaocooperado.cucumber.stepdefs.servicosexternos;

import br.com.dbccompany.votacaocooperado.client.stub.UsuarioClientStub;
import br.com.dbccompany.votacaocooperado.cucumber.datatable.servicosexternos.JsonResponseDataTable;
import br.com.dbccompany.votacaocooperado.cucumber.stepdefs.StepDefs;
import io.cucumber.java.pt.E;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServicosExternosStepDefs extends StepDefs {

    private final UsuarioClientStub taxClientStub;

    @E("^que existam os seguintes responses disponiveis no endpoint findByCpf")
    public void queExistamOsSeguintesResponsesDisponiveisNoEndpointFindByCpf(List<JsonResponseDataTable> jsonResponseDataTableList) {
        jsonResponseDataTableList.forEach(taxClientStub::configurarEndpointFindByCpf);
    }
}