package br.com.projlib.bookshelf.infra.exception;

public class UsernameDuplicateException extends GenericException {

    public UsernameDuplicateException() {
        super("userAccount.exception.usernameDuplicate");
    }
}
