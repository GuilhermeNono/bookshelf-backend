package br.com.projlib.bookshelf.infra.exception;

public class PasswordDoesNotMatchException extends GenericException {

    public PasswordDoesNotMatchException() {
        super("userAccount.exception.passwordDoesNotMatch");
    }
}
