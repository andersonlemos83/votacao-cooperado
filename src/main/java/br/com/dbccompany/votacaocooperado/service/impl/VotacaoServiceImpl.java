package br.com.dbccompany.votacaocooperado.service.impl;

import br.com.dbccompany.votacaocooperado.domain.Votacao;
import br.com.dbccompany.votacaocooperado.repository.VotacaoRepository;
import br.com.dbccompany.votacaocooperado.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Override
    public List<Votacao> listarTodos() {
        Votacao votacao1 = new Votacao();
        votacao1.setId(1l);
        votacao1.setVoto("SIM");
        Votacao votacao2 = new Votacao();
        votacao2.setId(2l);
        votacao2.setVoto("NÃO");
        return Arrays.asList(votacao1, votacao2);
    }
}
