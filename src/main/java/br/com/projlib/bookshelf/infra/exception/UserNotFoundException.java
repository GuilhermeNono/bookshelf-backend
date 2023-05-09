package br.com.projlib.bookshelf.infra.exception;

public class UserNotFoundException extends GenericException {
    public UserNotFoundException(String message) {
        super(message);
        this.printStackTrace();
    }

    public UserNotFoundException() {
        super("User not found.");
        this.printStackTrace();
    }
}
