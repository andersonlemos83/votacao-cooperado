package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.web.dto.AssociadoDto;

public final class AssociadoDtoBuilder {

    private final AssociadoDto associadoDto = new AssociadoDto();

    public static AssociadoDtoBuilder umAssociado() {
        return new AssociadoDtoBuilder();
    }

    public static AssociadoDtoBuilder umAssociadoQualquer() {
        return umAssociado().comId(1L).comNome("Klaus Meine").comCpf("78186436057");
    }

    public AssociadoDtoBuilder comId(Long id) {
        associadoDto.setId(id);
        return this;
    }

    public AssociadoDtoBuilder comNome(String nome) {
        associadoDto.setNome(nome);
        return this;
    }

    public AssociadoDtoBuilder comCpf(String cpf) {
        associadoDto.setCpf(cpf);
        return this;
    }

    public AssociadoDto build() {
        return associadoDto;
    }
}