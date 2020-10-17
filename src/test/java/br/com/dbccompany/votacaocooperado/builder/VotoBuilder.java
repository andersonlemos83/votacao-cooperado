package br.com.dbccompany.votacaocooperado.builder;

import br.com.dbccompany.votacaocooperado.domain.Assembleia;
import br.com.dbccompany.votacaocooperado.domain.Associado;
import br.com.dbccompany.votacaocooperado.domain.TipoVoto;
import br.com.dbccompany.votacaocooperado.domain.Voto;

import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.NAO;
import static br.com.dbccompany.votacaocooperado.domain.TipoVoto.SIM;

public final class VotoBuilder {

    private Voto voto = new Voto();

    public static VotoBuilder umVoto() {
        return new VotoBuilder();
    }

    public static VotoBuilder umVotoQualquer() {
        return umVoto()
                .comId(1l)
                .comTipoVoto(SIM)
                .comAssociado(AssociadoBuilder.umAssociadoQualquer().build())
                .comAssembleia(AssembleiaBuilder.umaAssembleiaQualquer().build());
    }

    public static VotoBuilder umVotoQualquerSim() {
        return umVotoQualquer().comTipoVoto(SIM);
    }

    public static VotoBuilder umVotoQualquerNao() {
        return umVotoQualquer().comTipoVoto(NAO);
    }

    public VotoBuilder comId(Long id) {
        voto.setId(id);
        return this;
    }

    public VotoBuilder comTipoVoto(TipoVoto tipoVoto) {
        voto.setTipoVoto(tipoVoto);
        return this;
    }

    public VotoBuilder comAssociado(Associado associado) {
        voto.setAssociado(associado);
        return this;
    }

    public VotoBuilder comAssembleia(Assembleia assembleia) {
        voto.setAssembleia(assembleia);
        return this;
    }

    public Voto build() {
        return voto;
    }
}