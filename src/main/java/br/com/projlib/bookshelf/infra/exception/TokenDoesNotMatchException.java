package br.com.projlib.bookshelf.infra.exception;

public class TokenDoesNotMatchException extends GenericException {

    public TokenDoesNotMatchException(String message) {
        super(message);
    }

    public TokenDoesNotMatchException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
