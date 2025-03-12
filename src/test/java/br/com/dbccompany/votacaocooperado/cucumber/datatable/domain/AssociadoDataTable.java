package br.com.dbccompany.votacaocooperado.cucumber.datatable.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDataTable implements Serializable {

    private Long id;
    private String nome;
    private String cpf;

}