package br.com.dbccompany.votacaocooperado.cucumber.datatable;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PautaDataTable implements Serializable {

    private Long id;
    private String descricao;

}