package br.com.dbccompany.votacaocooperado.shared.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

}