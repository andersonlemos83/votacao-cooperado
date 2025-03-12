package br.com.dbccompany.votacaocooperado.helper.gerenciador;

public interface GerenciadorH2 {

    void removerChavesEstrangeiras();

    void limparBanco();

    void resetarSequences();

}