package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Pauta;

import java.util.Date;

public final class AssembleiaBuilder {

    private Assembleia assembleia = new Assembleia();

    public static AssembleiaBuilder umaAssembleia() {
        return new AssembleiaBuilder();
    }

    public static AssembleiaBuilder umaAssembleiaQualquer() {
        return umaAssembleia()
                .comId(1l)
                .comDataCriacao(new Date())
                .comTempoDuracao(1)
                .comPauta(PautaBuilder.umaPautaQualquer().build());
    }

    public AssembleiaBuilder comId(Long id) {
        assembleia.setId(id);
        return this;
    }

    public AssembleiaBuilder comDataCriacao(Date dataCriacao) {
        assembleia.setDataCriacao(dataCriacao);
        return this;
    }

    public AssembleiaBuilder comTempoDuracao(int tempoDuracao) {
        assembleia.setTempoDuracao(tempoDuracao);
        return this;
    }

    public AssembleiaBuilder comPauta(Pauta pauta) {
        assembleia.setPauta(pauta);
        return this;
    }

    public Assembleia build() {
        return assembleia;
    }
}