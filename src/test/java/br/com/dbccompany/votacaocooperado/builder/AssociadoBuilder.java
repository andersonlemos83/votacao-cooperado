package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.domain.Associado;

public final class AssociadoBuilder {

    private final Associado associado = new Associado();

    public static AssociadoBuilder umAssociado() {
        return new AssociadoBuilder();
    }

    public static AssociadoBuilder umAssociadoQualquer() {
        return umAssociado().comId(1L).comNome("Klaus Meine").comCpf("78186436057");
    }

    public AssociadoBuilder comId(Long id) {
        associado.setId(id);
        return this;
    }

    public AssociadoBuilder comNome(String nome) {
        associado.setNome(nome);
        return this;
    }

    public AssociadoBuilder comCpf(String cpf) {
        associado.setCpf(cpf);
        return this;
    }

    public Associado build() {
        return associado;
    }
}