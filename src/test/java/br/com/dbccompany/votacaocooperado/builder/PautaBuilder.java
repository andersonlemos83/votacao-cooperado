package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.domain.Pauta;

public final class PautaBuilder {

    private Pauta pauta = new Pauta();

    public static PautaBuilder umaPauta() {
        return new PautaBuilder();
    }

    public static PautaBuilder umaPautaQualquer() {
        return umaPauta().comId(1l).comDecricao("Emissão de novas cotas do fundo Musical");
    }

    public PautaBuilder comId(Long id) {
        pauta.setId(id);
        return this;
    }

    public PautaBuilder comDecricao(String descricao) {
        pauta.setDescricao(descricao);
        return this;
    }

    public Pauta build() {
        return pauta;
    }
}