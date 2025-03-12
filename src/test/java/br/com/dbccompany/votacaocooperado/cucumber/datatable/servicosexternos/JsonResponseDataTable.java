package br.com.dbccompany.votacaocooperado.cucumber.datatable.servicosexternos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponseDataTable implements Serializable {

    private Object key;
    private HttpStatus status;
    private String response;

    public boolean isStatusOk() {
        return OK.equals(status);
    }

    public boolean isStatusBadRequest() {
        return BAD_REQUEST.equals(status);
    }
}